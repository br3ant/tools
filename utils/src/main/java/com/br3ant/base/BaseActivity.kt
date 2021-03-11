package com.br3ant.base

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.weikaiyun.fragmentation.SupportActivity

/**
 * 基类Activity
 */
abstract class BaseActivity(private val layout: Int = 0, private val fullScreen: Boolean = false, private val keepScreenOn: Boolean = false) : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (getThemeId() != 0) {
            setTheme(getThemeId())
        }
        super.onCreate(savedInstanceState)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (fullScreen) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        //全面屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp = window.attributes
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
            window.attributes = lp
        }

        try {
            val id = if (layout == 0) getLayoutId() else layout
            if (id == 0) throw IllegalAccessException("layoutId 不能为空")

            setContentView(id)
            initView()

            if (keepScreenOn) {
                window.decorView.findViewById<View>(android.R.id.content).keepScreenOn = true
            }
        } catch (e: Exception) {
            Log.e("BaseActivity", "${javaClass.simpleName} --> initView Error!! e = ${e.message}")
            e.printStackTrace()
        }
    }

    protected open fun getLayoutId(): Int = 0
    protected open fun getThemeId(): Int = 0
    protected abstract fun initView()
}