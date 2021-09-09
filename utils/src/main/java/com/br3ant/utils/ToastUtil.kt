package com.br3ant.utils

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.blankj.utilcode.util.ThreadUtils
import com.blankj.utilcode.util.Utils
import com.br3ant.ext.drawableOf
import com.br3ant.ext.errorMsg
import com.br3ant.ext.stringOf
import com.br3ant.ext.tintColorOf

import java.lang.ref.WeakReference

/**
 * @author datedu
 * @since 2017/12/25
 * Toast工具类，可避免吐司长时间显示
 */
object ToastUtil {
    @ColorInt
    private val DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF")

    @ColorInt
    private val ERROR_COLOR = Color.parseColor("#FD4C5B")

    @ColorInt
    private val INFO_COLOR = Color.parseColor("#3F51B5")

    @ColorInt
    private val SUCCESS_COLOR = Color.parseColor("#388E3C")

    @ColorInt
    private val WARNING_COLOR = Color.parseColor("#FFA900")

    @ColorInt
    private val NORMAL_COLOR = Color.parseColor("#71000000")

    private const val TOAST_TYPEFACE = "sans-serif-condensed"

    private var currentToast: WeakReference<Toast>? = null

    @JvmStatic
    @JvmOverloads
    fun normal(message: String, duration: Int = Toast.LENGTH_SHORT) {
        return ThreadUtils.runOnUiThread { custom(Utils.getApp(), message, null, DEFAULT_TEXT_COLOR, NORMAL_COLOR, duration).show() }
    }

    @JvmStatic
    @JvmOverloads
    fun warning(message: String, duration: Int = Toast.LENGTH_SHORT) {
        return ThreadUtils.runOnUiThread { custom(Utils.getApp(), message, R.drawable.ic_error_outline_white_48dp, DEFAULT_TEXT_COLOR, WARNING_COLOR, duration).show() }
    }

    @JvmStatic
    @JvmOverloads
    fun info(message: String, duration: Int = Toast.LENGTH_SHORT) {
        return ThreadUtils.runOnUiThread { custom(Utils.getApp(), message, R.drawable.ic_info_outline_white_48dp, DEFAULT_TEXT_COLOR, INFO_COLOR, duration).show() }
    }

    @JvmStatic
    @JvmOverloads
    fun success(message: String, duration: Int = Toast.LENGTH_SHORT) {
        return ThreadUtils.runOnUiThread { custom(Utils.getApp(), message, R.drawable.ic_check_white_48dp, DEFAULT_TEXT_COLOR, SUCCESS_COLOR, duration).show() }
    }

    @JvmStatic
    @JvmOverloads
    fun error(message: String, duration: Int = Toast.LENGTH_SHORT) {
        return ThreadUtils.runOnUiThread { custom(Utils.getApp(), message, R.drawable.ic_clear_white_48dp, DEFAULT_TEXT_COLOR, ERROR_COLOR, duration).show() }
    }

    @JvmStatic
    @JvmOverloads
    fun view(view: View, duration: Int = Toast.LENGTH_SHORT) {
        ThreadUtils.runOnUiThread { customView(view, duration).show() }
    }

    fun custom(context: Context, message: String, @DrawableRes iconRes: Int?, @ColorInt textColor: Int, @ColorInt tintColor: Int, durationTime: Int): Toast {
        currentToast?.get()?.cancel()
        return Toast(context).apply {
            val toastLayout = LayoutInflater.from(context).inflate(R.layout.layout_toast, null)
            val toastIcon = toastLayout.findViewById<ImageView>(R.id.toast_icon)
            val toastTextView = toastLayout.findViewById<TextView>(R.id.toast_text)
            toastLayout.background = drawableOf(R.drawable.toast_bg)?.tintColorOf(tintColor)
            iconRes?.let { iconRes ->
                toastIcon.setImageResource(iconRes)
            } ?: kotlin.run { toastIcon.visibility = View.GONE }
            toastTextView.setTextColor(textColor)
            toastTextView.text = message

            view = toastLayout
            duration = durationTime
            setGravity(Gravity.CENTER, 0, 0)

            currentToast = WeakReference(this)
        }
    }

    private fun customView(target: View, durationTime: Int): Toast {
        currentToast?.get()?.cancel()
        return Toast(Utils.getApp()).apply {
            view = target
            duration = durationTime
            setGravity(Gravity.CENTER, 0, 0)

            currentToast = WeakReference(this)
        }
    }
    

    /**
     * 封装了Toast的方法 :需要等待
     */
    @JvmStatic
    fun showAndroidToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(Utils.getApp(), message, duration).show()
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    @JvmStatic
    fun showAndroidToast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
        showAndroidToast(stringOf(resId), duration)
    }


    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param msg 显示内容
     */
    @JvmStatic
    fun showToast(msg: String?) {
        normal(msg ?: return, Toast.LENGTH_SHORT)
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param resId String资源ID
     */
    @JvmStatic
    fun showToast(resId: Int) {
        showToast(stringOf(resId))
    }

    @JvmStatic
    fun showToast(throwable: Throwable) {
        showToast(throwable.errorMsg)
    }

    @JvmStatic
    fun showView(@LayoutRes layoutId: Int, duration: Int = Toast.LENGTH_LONG) {
        view(LayoutInflater.from(Utils.getApp()).inflate(layoutId, null), duration)
    }

}