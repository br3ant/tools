package com.br3ant.tools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.br3ant.ext.launchWithCatch
import com.br3ant.ext.show
import com.br3ant.rxhttp.RxHttpUtils
import rxhttp.RxHttp
import rxhttp.toResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RxHttpUtils.init()
        lifecycleScope.launchWithCatch({
            RxHttp.get("")
                .toResponse<String>().await()
        }, onError = { it.show() })
    }
}