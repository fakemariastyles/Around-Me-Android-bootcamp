package com.workshop.aroundme.app.ui.login

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import com.workshop.aroundme.R


@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @Test
    fun show_dialog_when_username_or_password_is_invalid() {
        val scenario = launchFragmentInContainer<LoginFragment>()
        login("maryam" , "1234")
        onView(withText("Invalid username or password")).check(matches(isDisplayed()))
    }

    @Test
    fun verify_login(){
        with(launchFragmentInContainer<LoginFragment>()){
            login("reza" , "1234")
        }
    }

    private fun login(userName:String , password: String){
        onView(withId(R.id.login)).check(matches(withText("Login")))
        onView(withId(R.id.username)).perform(clearText() , typeText(userName))
        onView(withId(R.id.password)).perform(clearText() , typeText(password))
        onView(withId(R.id.login)).perform(click())
    }

}