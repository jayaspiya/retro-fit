package com.jayaspiya.retrofittry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.jayaspiya.retrofittry.model.User
import com.jayaspiya.retrofittry.repo.UserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var tvRegister: TextView
    private lateinit var btnLogin: Button
    private lateinit var chkRememberMe: CheckBox
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        tvRegister = findViewById(R.id.tvRegister)
        btnLogin = findViewById(R.id.btnLogin)

        tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val user = User(username = etUsername.text.toString(), password = etPassword.text.toString())
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val repo = UserRepo()
                val response = repo.loginUser(user)
                if(response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@LoginActivity, "User Login", Toast.LENGTH_SHORT).show()
                    }
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
                else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@LoginActivity, "Invalid Credential", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch(ex:ExceptionInInitializerError){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@LoginActivity, "error $ex", Toast.LENGTH_SHORT).show()
                }  
            }
        }
    }
}