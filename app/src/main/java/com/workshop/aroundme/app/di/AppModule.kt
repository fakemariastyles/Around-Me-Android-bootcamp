package com.workshop.aroundme.app.di

import android.content.Context
import android.content.SharedPreferences
import com.workshop.aroundme.app.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val myApp: MyApp) {


    @Provides
    @Singleton
    fun provideContext(): Context {
        return myApp.applicationContext
    }

    @Provides
    @Singleton
    fun provideSharedPrefrences(context: Context): SharedPreferences {
        return context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
    }

}