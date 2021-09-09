package com.br3ant.ext

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/9/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
fun Boolean?.isNullOrFalse(): Boolean {
    return this == null || this == false
}

fun Boolean?.isTrue(): Boolean {
    return this == true
}