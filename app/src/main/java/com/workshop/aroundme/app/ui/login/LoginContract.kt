
package com.workshop.aroundme.app.ui.login

import androidx.annotation.StringRes

interface LoginContract {

    interface View : LoginContract {

        fun getUsernameValue(): String

        fun getPasswordValue(): String

        fun showHomeFragment()

        fun showMessageToUser(@StringRes message: Int, @StringRes title: Int)

    }

    interface Presenter : LoginContract {

        fun onLoginButtonClicked()

    }

}