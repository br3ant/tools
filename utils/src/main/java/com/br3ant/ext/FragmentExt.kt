package com.br3ant.ext

import androidx.fragment.app.Fragment

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/9/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

inline fun <reified T : Any> Fragment.getValue(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}

inline fun <reified T : Any> Fragment.getValueNonNull(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

