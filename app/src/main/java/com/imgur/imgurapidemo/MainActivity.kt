// Author:           Sunitha Premjee

package com.imgur.imgurapidemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.library.BuildConfig
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Timber.d("MainActivity:onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
