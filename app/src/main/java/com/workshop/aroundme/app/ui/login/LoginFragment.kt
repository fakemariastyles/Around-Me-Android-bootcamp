package com.workshop.aroundme.app.ui.login


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R
import com.workshop.aroundme.app.MyApp
import com.workshop.aroundme.app.ui.home.view.HomeFragment
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.data.repository.UserRepository
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @SuppressLint("ApplySharedPref")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        view.findViewById<View>(R.id.login).setOnClickListener {

            if (usernameEditText.text.isNotEmpty() && usernameEditText.text.toString() == "reza"
                && passwordEditText.text.isNotEmpty() && passwordEditText.text.toString() == "1234"
            ) {

                val user = UserEntity(usernameEditText.text.toString())
                userRepository.login(user)

                fragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, HomeFragment())
                    ?.commit()
            } else {
                AlertDialog.Builder(view.context)
                    .setTitle(getString(R.string.error))
                    .setMessage(getString(R.string.invalid_user_or_pass))
                    .setPositiveButton(getString(R.string.ok)) { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                    }
                    .create()
                    .show()

            }
        }
    }

}
