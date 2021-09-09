package com.br3ant.ext

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

/**
 * <pre>
 *     copyright: mukun
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2021 09 2021/9/6
 *     desc   :
 *     version: 1.0
 * </pre>
 */

fun CoroutineScope.launchWithCatch(
    block: suspend CoroutineScope.() -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    onStart: (() -> Unit)? = null,
    onFinally: (() -> Unit)? = null
): Job {
    return this.launch {
        try {
            onStart?.invoke()
            block()
        } catch (t: Throwable) {
            if (onError != null && isActive) {
                try {
                    onError(t)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            } else {
                t.printStackTrace()
            }
        } finally {
            onFinally?.let { it() }
        }
    }
}

/**
 *     请不要滥用，否则可能达不到预期!!!
 *     如果在fragment，activity 请使用 @link{LifecycleOwner.rxLifeScope}
 *     如果在View中，请使用@link{androidx.lifecycle.ViewTreeLifecycleOwner}获取最近的LifecycleOwner
 *     此处获取的一定是Activity或者Service的LifecycleOwner
 */
val Context.scopeInContext: CoroutineScope
    get() = when (this) {
        is AppCompatActivity -> {
            this.lifecycleScope
        }
        is LifecycleService -> {
            this.lifecycleScope
        }
        else -> {
            CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        }
    }