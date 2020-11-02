package com.marvel.avengers

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.Intent as Intent

class LoginActivity : AppCompatActivity() {
    lateinit var etMobileNumber: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogIn: Button
    lateinit var txtForgotPassword: TextView
    lateinit var txtRegisterYourself: TextView
    val validMobileNumber = "1234567890"
    val validPassword = arrayOf("ironMan", "thor", "avengers")
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        setContentView(R.layout.login_activity)
        if (isLoggedIn) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        title = "Log in"
        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        btnLogIn = findViewById(R.id.btnLogIn)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        txtRegisterYourself = findViewById(R.id.txtRegisterYourself)
        btnLogIn.setOnClickListener {
            val mobileNumber = etMobileNumber.text.toString()
            val password = etPassword.text.toString()
            var nameOfAvenger = "Avengers"
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            if (mobileNumber == validMobileNumber) {
                if (password == validPassword[0]) {
                    nameOfAvenger = "Ironman"
                    savePreferences(nameOfAvenger)
                    startActivity(intent)
                } else if (password == validPassword[1]) {
                    nameOfAvenger = "Thor"
                    savePreferences(nameOfAvenger)
                    startActivity(intent)
                } else if (password == validPassword[2]) {
                    nameOfAvenger = "Avengers"
                    savePreferences(nameOfAvenger)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this@LoginActivity, "Incorrect Credentials", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun savePreferences(title: String) {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("Title", title).apply()
    }
}