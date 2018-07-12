package com.example.myreditt.core.di

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface BaseComponent {

    fun context(): Context

    fun retrofit(): Retrofit
}