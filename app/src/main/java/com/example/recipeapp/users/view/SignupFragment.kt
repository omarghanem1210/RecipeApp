package com.example.recipeapp.users.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.models.User
import com.google.gson.Gson
import java.math.BigInteger
import java.security.MessageDigest

class SignupFragment: Fragment() {
    lateinit var usernameTextEdit: EditText
    lateinit var passwordTextEdit:EditText
    lateinit var confirmedPasswordTextEdit:EditText
    lateinit var signupButton: Button

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.signup, container, false)
        usernameTextEdit = view.findViewById(R.id.username)
        passwordTextEdit = view.findViewById(R.id.password)
        confirmedPasswordTextEdit = view.findViewById(R.id.confirmPassword)
        signupButton = view.findViewById(R.id.signupButton)
        val loginButton: Button = view.findViewById(R.id.loginLink)
        val databaseHelper = DatabaseHeLper(requireContext())

        loginButton.setOnClickListener{
            clear()
            val action = SignupFragmentDirections.signupFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }

        signupButton.setOnClickListener{
            val username = usernameTextEdit.text.toString()
            val password = passwordTextEdit.text.toString()
            val hashedPassword = md5(password)
            val oldUser = databaseHelper.readUser(username, hashedPassword)

            if(password != confirmedPasswordTextEdit.text.toString()){
                Toast.makeText(requireContext(), "Password Written Incorrectly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if (oldUser != null){
                Toast.makeText(requireContext(), "Username Already Exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            databaseHelper.insertUser(username, hashedPassword)
            val action = SignupFragmentDirections.signupFragmentToLoginFragment()
            clear()
            view.findNavController().navigate(action)

        }

        return view
    }

    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


    fun deleteUser() {
        val editor = sharedPreferences.edit()
        editor.remove("logged")
        editor.apply()
    }

    fun clear(){
        usernameTextEdit.setText("")
        passwordTextEdit.setText("")
        confirmedPasswordTextEdit.setText("")
    }



}



