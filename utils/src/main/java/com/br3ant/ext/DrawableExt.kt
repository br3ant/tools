package com.br3ant.ext

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.graphics.drawable.DrawableCompat


/**
 * 对 Drawable 进行着色
 *
 * @param color 颜色
 * @return 着色之后的 Drawable
 */
fun Drawable.tintOf(color: String): Drawable = tintColorOf(colorOf(color))

fun Drawable.tintOf(@ColorRes color: Int): Drawable = tintColorOf(colorOf(color))

fun Drawable.tintColorOf(@ColorInt color: Int): Drawable {
    val wrappedDrawable: Drawable = DrawableCompat.wrap(this.mutate())
    DrawableCompat.setTintList(wrappedDrawable, ColorStateList.valueOf(color))
    return wrappedDrawable
}

/**
 * 获取带圆角的 drawable，各个圆角都为 radius，这里只是总结了比较常用的获取 Drawable 的方式
 *
 * @param color  solid color
 * @param radius radius of every corner
 * @return       the drawable
 */
fun getDrawable(
    @ColorInt color: Int,
    radius: Float
): Drawable {
    val drawable = GradientDrawable()
    drawable.setColor(color)
    drawable.cornerRadius = radius
    return drawable
}

fun getDrawable(
    @ColorInt color: Int,
    radius: Float,
    strokeWidth: Int,
    @ColorInt strokeColor: Int
): Drawable {
    val drawable = GradientDrawable()
    drawable.setColor(color)
    drawable.cornerRadius = radius
    drawable.setStroke(strokeWidth, strokeColor)
    return drawable
}

/**
 * 获取带圆角的 drawable
 *
 * @param color             the solid color
 * @param topLeftRadius     the top left radius
 * @param topRightRadius    the top right radius
 * @param bottomLeftRadius  the bottom left radius
 * @param bottomRightRadius the bottom right radius
 * @return                  the drawable
 */
fun getDrawable(
    @ColorInt color: Int,
    topLeftRadius: Float,
    topRightRadius: Float,
    bottomLeftRadius: Float,
    bottomRightRadius: Float
): Drawable {
    val drawable = GradientDrawable()
    drawable.setColor(color)
    drawable.cornerRadii = floatArrayOf(
        topLeftRadius, topLeftRadius,
        topRightRadius, topRightRadius,
        bottomRightRadius, bottomRightRadius,
        bottomLeftRadius, bottomLeftRadius
    )
    return drawable
}

fun getDrawable(
    @ColorInt color: Int,
    topLeftRadius: Float,
    topRightRadius: Float,
    bottomLeftRadius: Float,
    bottomRightRadius: Float,
    strokeWidth: Int,
    @ColorInt strokeColor: Int
): Drawable {
    val drawable = GradientDrawable()
    drawable.setColor(color)
    drawable.setStroke(strokeWidth, strokeColor)
    drawable.cornerRadii = floatArrayOf(
        topLeftRadius, topLeftRadius,
        topRightRadius, topRightRadius,
        bottomRightRadius, bottomRightRadius,
        bottomLeftRadius, bottomLeftRadius
    )
    return drawable
}


