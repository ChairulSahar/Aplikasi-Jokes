package com.d121211011.jokes

import android.app.Application
import com.d121211011.jokes.data.AppContainer
import com.d121211011.jokes.data.DefaultAppContainer

class MyApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}