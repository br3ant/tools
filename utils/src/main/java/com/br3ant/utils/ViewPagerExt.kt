package com.br3ant.utils

import androidx.viewpager.widget.ViewPager

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/3/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */

fun ViewPager.onPageSelected(action: (newPosition: Int) -> Unit) =
        addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                action(position)
            }
        })