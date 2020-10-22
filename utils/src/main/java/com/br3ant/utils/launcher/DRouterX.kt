package com.br3ant.utils.launcher

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import java.util.*

/**
 * <pre>
 * copyright: datedu
 * @author : houqiqi
 * e-mail : xxx@xx
 * time   : 2019-11-21
 * desc   : 把OnActivityResult方式转换为Callback方式的空Fragment（X兼容包）
 * version: 1.0
</pre> *
 */
class DRouterX : Fragment() {
    private val mCallbacks = SparseArray<DLauncher.Callback?>()
    private val mCodeGenerator = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun startActivityForResult(intent: Intent?, callback: DLauncher.Callback?) {
        val requestCode = makeRequestCode()
        mCallbacks.put(requestCode, callback)
        startActivityForResult(intent, requestCode)
    }

    /**
     * 随机生成唯一的requestCode，最多尝试10次
     *
     * @return
     */
    private fun makeRequestCode(): Int {
        var requestCode: Int
        var tryCount = 0
        do {
            requestCode = mCodeGenerator.nextInt(0x0000FFFF)
            tryCount++
        } while (mCallbacks.indexOfKey(requestCode) >= 0 && tryCount < 10)
        return requestCode
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val callback = mCallbacks[requestCode]
        mCallbacks.remove(requestCode)
        callback?.onActivityResult(resultCode, data)
    }

    companion object {
        fun newInstance(): DRouterX {
            return DRouterX()
        }
    }
}