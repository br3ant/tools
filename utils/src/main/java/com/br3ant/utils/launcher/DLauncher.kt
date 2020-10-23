package com.br3ant.utils.launcher

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.br3ant.utils.getIntent
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * <pre>
 * copyright: datedu
 * @author : houqiqi
 * e-mail : xxx@xx
 * time   : 2019-11-21
 * desc   : Activity跳转封装类，把OnActivityResult方式改为Callback方式
 * version: 1.0
</pre> *
 */
class DLauncher constructor(activity: FragmentActivity) {
    val mContext: Context
    private val mRouterFragmentX: DRouterX?

    constructor(fragment: Fragment) : this(fragment.requireActivity())

    constructor(activity: Activity) : this(activity as FragmentActivity)


    private fun getRouterFragmentX(activity: FragmentActivity): DRouterX? {

        return findRouterFragmentX(activity) ?: DRouterX.newInstance().also {
            val fragmentManager = activity.supportFragmentManager
            fragmentManager
                .beginTransaction()
                .add(it, TAG)
                .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
    }

    private fun findRouterFragmentX(activity: FragmentActivity): DRouterX? {
        return activity.supportFragmentManager.findFragmentByTag(TAG) as DRouterX?
    }

    fun startActivityForResult(clazz: Class<*>?, callback: Callback?) {
        val intent = Intent(mContext, clazz)
        startActivityForResult(intent, callback)
    }

    fun startActivityForResult(intent: Intent?, callback: Callback?) {
        if (mRouterFragmentX != null) {
            mRouterFragmentX.startActivityForResult(intent, callback)
        } else {
            throw RuntimeException("please do init first!")
        }
    }

    suspend inline fun <reified T : Context> startActivityForResult(vararg pairs: Pair<String, Any>): Bundle? =
        suspendCancellableCoroutine { continuation ->
            try {
                startActivityForResult(Intent(mContext.getIntent<T>(*pairs))) { _, data ->
                    continuation.resume(data?.extras)
                }
            } catch (e: Exception) {
                continuation.resumeWithException(e)
            }
        }

    fun interface Callback {
        fun onActivityResult(resultCode: Int, data: Intent?)
    }

    companion object {
        private const val TAG = "DLauncher"
    }

    init {
        mContext = activity
        mRouterFragmentX = getRouterFragmentX(activity)
    }
}