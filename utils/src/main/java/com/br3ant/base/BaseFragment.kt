package com.br3ant.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.weikaiyun.fragmentation.SupportFragment

/**
 * layout必须传，使用viewBing也要传
 */
abstract class BaseFragment(private val layout: Int = 0) : SupportFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val id = if (layout == 0) getLayoutId() else layout
        return inflater.inflate(id, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun lazyInit() {
        super.lazyInit()
        observeData()
    }

    fun <T : View> findViewById(@IdRes viewId: Int): T? = view?.findViewById(viewId)

    @Deprecated("使用构造函数传递或者使用viewBing", ReplaceWith("viewBing"))
    protected open fun getLayoutId(): Int = 0
    protected open fun observeData() {}
    protected abstract fun initView()
}