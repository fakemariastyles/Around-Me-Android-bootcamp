package com.workshop.aroundme.app

import android.app.Application
import com.workshop.aroundme.app.di.AppComponent
import com.workshop.aroundme.app.di.AppModule
import com.workshop.aroundme.app.di.DaggerAppComponent

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var component: AppComponent
    }
}