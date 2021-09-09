package com.br3ant.ext

import java.util.*

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 11/16/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
fun Float.keepDot(dot: Int = 1): Float {
    return String.format(Locale.CHINA, "%.${dot}f", this).toFloat()
}

fun Float.formatPercentage(dot: Int = 1): String {
    return "${(this * 100).keepDot(dot)}%"
}

/**
 * String 需要是Float格式
 */
fun String.keepDot(dot: Int = 1): Float {
    return (this.toFloatOrNull() ?: 0f).keepDot(dot)
}

fun String.formatPercentage(dot: Int = 1): String {
    return (this.toFloatOrNull() ?: 0f).formatPercentage(dot)
}