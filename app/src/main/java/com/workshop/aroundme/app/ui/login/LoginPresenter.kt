package com.workshop.aroundme.app.ui.login

import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.data.repository.UserRepository
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val userRepository: UserRepository
) : LoginContract.Presenter {
    lateinit var view: LoginContract.View
    override fun onLoginButtonClicked() {
        val userName = view.getUsernameValue()
        val password = view.getPasswordValue()

        if (userName.isNotEmpty() && userName == "reza"
            && password.isNotEmpty() && password == "1234"
        ) {
            val user = UserEntity(userName)
            userRepository.login(user)
            view.showHomeFragment()
        } else {
            view.showMessageToUser(R.string.invalid_user_or_pass, R.string.error)
        }

    }

}