package com.br3ant.utils



/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/4/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
fun Any.toJson(): String = GsonUtil.jsonCreate(this)

inline fun <reified T> String?.toBean(): T? = GsonUtil.json2Bean(this, T::class.java)

inline fun <reified T> String?.toBeanElse(t: T): T = GsonUtil.json2Bean(this, T::class.java) ?: t

inline fun <reified T> String?.toList(): List<T> = GsonUtil.json2List(this, T::class.java)
        ?: emptyList()


