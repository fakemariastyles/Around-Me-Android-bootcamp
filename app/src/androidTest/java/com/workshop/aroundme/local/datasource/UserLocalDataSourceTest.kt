package com.workshop.aroundme.local.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.workshop.aroundme.data.model.UserEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Test
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

    @Test
    fun return_empty_when_not_login(){
        val user = userLocalDataSource.getUser()
        assertNull(user)
    }

    @Test
    fun returns_user_after_login(){
        val user = UserEntity("mary")
        userLocalDataSource.login(user)
        val result = userLocalDataSource.getUser()
        assertEquals(result , user)
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