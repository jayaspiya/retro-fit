package com.jayaspiya.retrofittry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jayaspiya.retrofittry.model.User
import com.jayaspiya.retrofittry.repo.UserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {
    private lateinit var etFname: EditText
    private lateinit var etLname: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnAddUser: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etFname = findViewById(R.id.etFname)
        etLname = findViewById(R.id.etLname)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnAddUser = findViewById(R.id.btnAddUser)

        btnAddUser.setOnClickListener {

            val fname = etFname.text.toString()
            val lname = etLname.text.toString()
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (password != confirmPassword) {
                etPassword.error = "Password does not match"
                etPassword.requestFocus()
                return@setOnClickListener
            } else {
                val user = User(fname=fname, lname = lname, username = username, password = password)
                CoroutineScope(Dispatchers.IO).launch{
                    try {
                        val repo = UserRepo()
                        val response = repo.registerUser(user)
                        if(response.success == true){
                            withContext(Dispatchers.Main){
                                Toast.makeText(this@RegisterActivity, "User Registered", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                    catch (ex: Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@RegisterActivity, "error $ex", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}