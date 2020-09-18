package com.br3ant.base

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.gyf.immersionbar.ImmersionBar
import me.yokeyword.fragmentation.SupportActivity

/**
 * 基类Activity
 */
abstract class BaseActivity(
    private val layout: Int = 0,
    private val fullScreen: Boolean = false,
    private val keepScreenOn: Boolean = false
) : SupportActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (getThemeId() != 0) {
            setTheme(getThemeId())
        }
        super.onCreate(savedInstanceState)

        window.clearFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (isFullScreen()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        //全面屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp = window.attributes
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
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

        //由于状态栏颜色修改为白色，所以需要把状态栏文字颜色改为深色，必须放在布局创建后
        //全屏显示的界面不要用这个，会导致横屏的刘海颜色被设置为状态栏颜色
        if (!isFullScreen() && isUseImmersionBar()) {
            //1:MIUUI 2:Flyme 3:android6.0 otherwise 设置0.2f透明度

            //不设置fitsSystemWindows会导致状态栏被挡住，设置后会导致全屏界面上面有白条
            ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .keyboardEnable(true)
                .statusBarColor("#ffffff")
                .fitsSystemWindows(true)
                .init()
        }
    }

    protected open fun isFullScreen(): Boolean {
        return fullScreen
    }

    //是否使用ImmersionBar
    protected open fun isUseImmersionBar(): Boolean = true

    protected open fun getLayoutId(): Int = 0
    protected open fun getThemeId(): Int = 0
    protected abstract fun initView()
}