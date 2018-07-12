package com.example.myreditt.core.di

import com.example.myreditt.base.BaseApplication
import com.example.myreditt.home.di.DaggerHomeComponent
import com.example.myreditt.home.di.HomeComponent
import javax.inject.Singleton

@Singleton
object DIHandler {

    private var homeComponent: HomeComponent? = null

    /*
    * Fetch the component dependency
    * */
    fun getHomeComponent(): HomeComponent {
        if (homeComponent == null) {
            homeComponent = DaggerHomeComponent.builder().baseComponent(BaseApplication.baseComponent).build()
        }
        return homeComponent as HomeComponent
    }

    fun destroyContainerComponent() {
        homeComponent = null
    }

}
