package com.br3ant.utils.rxhttp.interceptor

import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.EOFException
import java.io.IOException
import java.nio.charset.StandardCharsets


class LogInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        logForRequest(request)
        val response: Response = chain.proceed(request)
        logForResponse(response)
        return response
    }


    private fun logForRequest(request: Request) {
        try {
            val requestBody = request.body
            if (requestBody != null) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)
                val charset = requestBody.contentType()?.charset(UTF8)
                val body = buffer.readString(charset ?: UTF8)
                LogUtils.iTag(TAG, "method = ${request.method} -- url = ${request.url}?${body}")
            } else {
                LogUtils.iTag(TAG, "method = ${request.method} -- url = ${request.url}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun logForResponse(response: Response?) {
        val body = response?.body ?: return
        try {
            val source = body.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer
            if (!isPlaintext(buffer)) {
                return
            }
            if (body.contentLength() != 0L) {
                val string = buffer.clone().readString(body.contentType()?.charset(UTF8) ?: UTF8)
                LogUtils.iTag(TAG, "response = $string")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun isPlaintext(buffer: Buffer): Boolean {
        return try {
            val prefix = Buffer()
            val byteCount = if (buffer.size < 64) buffer.size else 64
            buffer.copyTo(prefix, 0, byteCount)
            for (i in 0..15) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            true
        } catch (e: EOFException) {
            false
        }
    }


    companion object {
        private val UTF8 = StandardCharsets.UTF_8
        private const val TAG = "OkHttp"
    }
}