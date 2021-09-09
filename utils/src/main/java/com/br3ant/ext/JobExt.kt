package com.br3ant.ext

import kotlinx.coroutines.Job

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
fun Job?.isRunning(): Boolean {
    return this != null && this.isActive
}