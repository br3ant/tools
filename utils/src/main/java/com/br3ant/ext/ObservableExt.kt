package com.br3ant.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/4/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
fun <T> Flowable<T>.toLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this)
}

fun <T> Observable<T>.toLiveData(backPressureStrategy: BackpressureStrategy = BackpressureStrategy.BUFFER): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable(backPressureStrategy))
}

fun <T> Single<T>.toLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
}

fun <T> Maybe<T>.toLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
}

fun <T> Completable.toLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
}

suspend fun <T> Observable<T>.await(): T = suspendCancellableCoroutine { continuation ->
    val disposable = this.subscribe({
        continuation.resume(it)
    }, {
        continuation.resumeWithException(it)
    })
    continuation.invokeOnCancellation { disposable.dispose() }
}

