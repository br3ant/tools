package com.br3ant.ext

import java.util.*

/**
 * <pre>
 * copyright: datedu
 * @author : br3ant
 * e-mail : xxx@xx
 * time   : 2020/4/27
 * desc   :
 * version: 1.0
</pre> *
 */
inline fun <T> List<T>.nextOrNull(predicate: (T) -> Boolean): T? {
    return this.getOrNull(this.indexOfFirst(predicate) + 1)
}

inline fun <T> List<T>.preOrNull(predicate: (T) -> Boolean): T? {
    return this.getOrNull(this.indexOfFirst(predicate) - 1)
}

fun <T> List<T>.groupListByQuantity(quantity: Int): List<List<T>>? {
    if (isEmpty() || quantity <= 0) {
        return null
    }
    val wrapList: MutableList<List<T>> = ArrayList()
    var count = 0
    while (count < size) {
        val num = count + quantity
        wrapList.add(ArrayList(subList(count, num.coerceAtMost(size))))
        count += quantity
    }
    return wrapList
}