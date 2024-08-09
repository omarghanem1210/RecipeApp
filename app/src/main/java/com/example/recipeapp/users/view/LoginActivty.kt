package com.example.recipeapp.users.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.databinding.ActivityLoginActivtyBinding

//import com.example.sql.databinding.ActivityLoginActivtyBinding

class LoginActivty : AppCompatActivity() {

    private lateinit var binding: ActivityLoginActivtyBinding
    private lateinit var databaseHelper: DatabaseHeLper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHeLper(this)

        binding.loginButton.setOnClickListener {
            val loginUsername = binding.loginUsername.text.toString()
            val loginPassword = binding.loginPassword.text.toString()
            loginDatabase(loginUsername, loginPassword)
        }
        binding.signupRegister.setOnClickListener(){
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginDatabase(username: String, password: String){
        val userExists = databaseHelper.readUser(username, password)
        if (userExists != null){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}