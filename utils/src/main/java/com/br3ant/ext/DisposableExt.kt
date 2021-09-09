package com.br3ant.ext

import io.reactivex.disposables.Disposable

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/4/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
fun Disposable?.isRunning(): Boolean {
    return this != null && !this.isDisposed
}