package com.example.davaleba_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_main)
        var tvEmail = findViewById<TextView>(R.id.emailLogin)
        var tvPassword = findViewById<TextView>(R.id.passwordLogin)
        var registerBtn = findViewById<Button>(R.id.registerButton)
        registerBtn.setOnClickListener {
            registerUser(tvEmail, tvPassword)`
        }
    }
    private fun registerUser(email: TextView, password: TextView){
        if(email.text.toString().isEmpty()){
            email.error = "Please enter email"
        }
        if(password.text.toString().isEmpty()){
            password.error = "Please enter password"
        } else {
            val email = email.text.toString()
            val password = password.text.toString()
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"$email $password registered",Toast.LENGTH_LONG).show()
                    } else {Toast.makeText(this,"Login Failed",Toast.LENGTH_LONG).show()}
                }
        }
    }

}