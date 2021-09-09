package com.br3ant.ext

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.Utils
import com.br3ant.utils.R


/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/10/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */

@ColorInt
fun colorOf(@ColorRes id: Int): Int = ContextCompat.getColor(Utils.getApp(), id)

@ColorInt
fun colorOf(color: String): Int = Color.parseColor(color)

@ColorInt
fun colorOf(context: Context, @AttrRes resId: Int = R.attr.colorPrimary): Int = TypedValue().apply {
    context.theme.resolveAttribute(resId, this, true)
}.data

fun stringOf(@StringRes id: Int): String = Utils.getApp().getString(id)

fun stringOf(@StringRes id: Int, vararg formatArgs: Any): String = Utils.getApp().getString(id, *formatArgs)

fun drawableOf(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(Utils.getApp(), id)

@AnyRes
fun identifierOf(name: String, defType: String, @AnyRes default: Int): Int {
    val resource = Utils.getApp().resources.getIdentifier(name, defType, Utils.getApp().packageName)
    return if (resource == 0) default else resource
}

fun dpOf(@DimenRes id: Int): Int = Utils.getApp().resources.getDimensionPixelSize(id)

fun dpOf(dp: Float): Int = (dp * Utils.getApp().resources.displayMetrics.density + 0.5f).toInt()

fun boolOf(@BoolRes id:Int) = Utils.getApp().resources.getBoolean(id)

