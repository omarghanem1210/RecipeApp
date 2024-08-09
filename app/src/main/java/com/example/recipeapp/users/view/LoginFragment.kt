package com.example.recipeapp.users.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.recipeapp.MainActivity
import com.example.recipeapp.R
import com.example.recipeapp.models.User
import com.example.recipeapp.models.UserManager
import com.google.gson.Gson
import java.math.BigInteger
import java.security.MessageDigest

class LoginFragment: Fragment() {

    lateinit var usernameTextEdit: EditText
    lateinit var passwordTextEdit: EditText
    lateinit var logInButton: Button

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login, container, false)
        usernameTextEdit = view.findViewById(R.id.username)
        passwordTextEdit = view.findViewById(R.id.password)
        logInButton = view.findViewById(R.id.loginButton)
        val databaseHeLper = DatabaseHeLper(requireContext())

        sharedPreferences = context?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)!!

        logInButton.setOnClickListener{
            val username = usernameTextEdit.text.toString()
            val passwordHash = md5(passwordTextEdit.text.toString())

            val user = databaseHeLper.readUser(username, passwordHash)
            if (user == null){
                Toast.makeText(requireContext(), "Username Doesn't Exist", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if(passwordHash != user.passwordHash){
                Toast.makeText(requireContext(), "Incorrect Password", Toast.LENGTH_SHORT).show()
            }

            else{
                UserManager.currentUser = user
                saveUser(user = user)
                val intent = Intent(getActivity(), com.example.recipeapp.recipes.view.MainActivity::class.java)
                activity?.startActivity(intent)


            }

        }


        return view
    }

    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun saveUser(user: User) {
        val json = gson.toJson(user)
        val editor = sharedPreferences.edit()
        editor.putString("logged", json)
        editor.apply()
    }




}