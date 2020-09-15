package com.br3ant.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import me.yokeyword.fragmentation.SupportFragment

abstract class BaseFragment(val layout: Int = 0) : SupportFragment() {
    protected lateinit var mRootView: View
    protected lateinit var mContext: Context
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!this::mRootView.isInitialized) {
            val id = if (layout == 0) getLayoutId() else layout
            if (id == 0) throw IllegalAccessException("layoutId 不能为空")

            mRootView = inflater.inflate(id, container, false)
            mContext = mRootView.context
        }
        return mRootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        observeData()
    }

    fun <T : View> findViewById(@IdRes id: Int): T {
        return mRootView.findViewById(id)
    }

    protected open fun getLayoutId(): Int = 0
    protected open fun observeData() {}
    protected abstract fun initView()
}