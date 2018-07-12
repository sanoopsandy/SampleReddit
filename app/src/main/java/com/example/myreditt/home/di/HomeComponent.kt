package com.example.myreditt.home.di

import com.example.myreditt.core.di.BaseComponent
import com.example.myreditt.core.networking.PostService
import com.example.myreditt.home.HomeActivity
import com.example.myreditt.singlePost.PostActivity
import com.example.nytapp.listModule.di.HomeScope
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@HomeScope
@Component(dependencies = [BaseComponent::class], modules = [HomeModule::class])
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
    fun inject(postActivity: PostActivity)
}

@HomeScope
@Module
class HomeModule {

    @HomeScope
    @Provides
    fun getPostService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)
}