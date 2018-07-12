package com.example.myreditt.base

import android.app.Application
import com.example.myreditt.core.Constants
import com.example.myreditt.core.di.AppModule
import com.example.myreditt.core.di.BaseComponent
import com.example.myreditt.core.di.DaggerBaseComponent
import com.example.myreditt.core.di.NetModule
import com.facebook.stetho.Stetho

class BaseApplication : Application() {

    companion object {
        lateinit var baseComponent: BaseComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        Stetho.initializeWithDefaults(this);
    }

    private fun initDI() {
        baseComponent = DaggerBaseComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(Constants.BASE_URL))
                .build()
    }

}