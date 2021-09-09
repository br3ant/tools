package com.br3ant.ext

import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

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

fun ViewPager.forward() = this.run { currentItem += 1 }

fun ViewPager.previous() = this.run { currentItem -= 1 }

fun ViewPager2.onPageSelected(action: (newPosition: Int) -> Unit) =
    this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            action(position)
        }
    })

fun ViewPager2.forward() = this.run { currentItem += 1 }

fun ViewPager2.previous() = this.run { currentItem -= 1 }