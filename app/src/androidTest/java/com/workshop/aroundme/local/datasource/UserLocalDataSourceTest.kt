package com.workshop.aroundme.local.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class UserLocalDataSourceTest {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userLocalDataSource: UserLocalDataSource

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        sharedPreferences = context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
        userLocalDataSource = UserLocalDataSource(sharedPreferences)
    }

    @After
    fun destroy() {
        sharedPreferences.clear()
    }
}

fun SharedPreferences.clear() {
    val edit = edit()
    all.forEach {
        edit.remove(it.key)
    }
    edit.commit()
}