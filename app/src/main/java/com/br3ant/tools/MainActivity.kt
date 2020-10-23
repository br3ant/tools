package com.br3ant.tools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br3ant.utils.launcher.DLauncher
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainScope().launch {
            val data =
                DLauncher(this@MainActivity).startActivityForResult<MainActivity>("demo" to "demo")?.getString("demo")
        }

    }
}