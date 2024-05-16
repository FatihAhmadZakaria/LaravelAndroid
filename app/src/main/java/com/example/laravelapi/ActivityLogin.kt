package com.example.laravelapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin : AppCompatActivity() {
    private lateinit var Button: Button
    private lateinit var EditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Register button click listener
        findViewById<Button>(R.id.register_button).setOnClickListener {
            val name = findViewById<EditText>(R.id.name_edit_text).text.toString()
            val email = findViewById<EditText>(R.id.email_edit_text).text.toString()
            val password = findViewById<EditText>(R.id.password_edit_text).text.toString()
            val password_c = findViewById<EditText>(R.id.password_c_edit_text).text.toString()

            register(name, email, password, password_c)
        }

        // Login button click listener
        findViewById<Button>(R.id.login_button).setOnClickListener {
            val email = findViewById<EditText>(R.id.email_edit_text).text.toString()
            val password = findViewById<EditText>(R.id.password_edit_text).text.toString()

            login(email, password)
        }
    }

    private fun register(name: String, email: String, password: String, password_c: String) {
        val user = User(name, email, password, password_c)

        RetrofitClient.apiService.register(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ActivityLogin, "User registered successfully", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@ActivityLogin, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@ActivityLogin, "Error: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun login(email: String, password: String) {
        val login = LoginResponse(email, password)

        RetrofitClient.apiService.login(login).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    Toast.makeText(this@ActivityLogin, "Berhasil login", Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(this@ActivityLogin, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@ActivityLogin, "Error: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        })
    }
}