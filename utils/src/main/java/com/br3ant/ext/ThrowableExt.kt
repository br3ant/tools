package com.br3ant.ext

import android.content.Context
import android.net.ConnectivityManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.br3ant.rxhttp.Response
import com.br3ant.utils.R
import com.br3ant.utils.ToastUtil
import com.br3ant.utils.toBean
import com.google.gson.JsonSyntaxException

import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


fun Throwable.show() {
    ToastUtil.showToast(errorMsg)
}

fun Throwable.loge() {
    LogUtils.e(errorMsg)
}

val Throwable.errorCode: Int
    get() {
        val errorCode = when (this) {
            is HttpStatusCodeException -> {//请求失败异常
                this.statusCode
            }
            is ParseException -> {  // ParseException异常表明请求成功，但是数据不正确
                this.errorCode
            }
            else -> {
                "-1"
            }
        }
        return try {
            errorCode.toInt()
        } catch (e: Exception) {
            -1
        }
    }


val Throwable.errorMsg: String
    get() {
        val errorMsg: String? = when (this) {
            //网络异常
            is UnknownHostException -> stringOf(if (!isNetworkConnected(Utils.getApp())) R.string.network_error else R.string.notify_no_network)
            //前者是通过OkHttpClient设置的超时引发的异常，后者是对单个请求调用timeout方法引发的超时异常
            is SocketTimeoutException, is TimeoutException -> stringOf(R.string.time_out_please_try_again_later)
            //连接异常
            is ConnectException -> stringOf(R.string.esky_service_exception)
            //请求失败异常
            is HttpStatusCodeException -> when (this.statusCode) {
                "416" -> "请求范围不符合要求"
                "404" -> "请求地址未找到"
                "401", "402" -> this.result?.toBean<Response<String>>()?.message ?: "token失效"
                else -> "请求异常，错误码：" + this.statusCode
            }
            //请求成功，但Json语法异常,导致解析失败
            is JsonSyntaxException -> "数据解析失败,请稍后再试"
            // ParseException异常表明请求成功，但是数据不正确 errorMsg为空，显示errorCode
            is ParseException -> this.message ?: errorCode
            else -> message
        }
        return errorMsg ?: this.toString()
    }

fun Throwable.isNetWorkError(): Boolean = this is UnknownHostException
        || this is SocketTimeoutException
        || this is TimeoutException
        || this is ConnectException

fun Throwable.isNetServerError(): Boolean = this is JsonSyntaxException
        || this is HttpStatusCodeException
        || this is ParseException

private fun isNetworkConnected(context: Context): Boolean {
    val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return mConnectivityManager.activeNetworkInfo?.isAvailable ?: false
}
