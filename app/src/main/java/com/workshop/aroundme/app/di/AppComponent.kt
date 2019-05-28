package com.workshop.aroundme.app.di

import com.workshop.aroundme.app.MainActivity
import com.workshop.aroundme.app.ui.detail.DetailFragment
import com.workshop.aroundme.app.ui.home.view.HomeFragment
import com.workshop.aroundme.app.ui.login.LoginFragment
import com.workshop.aroundme.app.ui.starred.StarredFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataBaseModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(detailFragment: DetailFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(starredFragment: StarredFragment)
}