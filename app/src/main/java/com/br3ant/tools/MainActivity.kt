package com.br3ant.tools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br3ant.utils.rxhttp.RxHttpUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RxHttpUtils.init()
    }
}