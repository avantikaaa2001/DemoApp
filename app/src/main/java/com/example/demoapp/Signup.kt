package com.example.demoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoapp.databinding.ActivitySigninBinding
import com.example.demoapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        binding.btnsignin.setOnClickListener {
            startActivity(Intent(this,Signin::class.java))
            finish()
        }
        binding.btnregister.setOnClickListener {
            val mail= binding.mail.text.toString()
            val username= binding.username.text.toString()
            val password = binding.password.text.toString()
            val repassword = binding.repassword.text.toString()
            if(mail.isEmpty() || username.isEmpty() || password.isEmpty() || repassword.isEmpty()){
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_LONG).show()
            } else if(password != repassword){
                Toast.makeText(this, "Repeat password must be same", Toast.LENGTH_LONG).show()
            } else (
                    auth.createUserWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this,Signin::class.java))
                                finish()

                            } else {
                                Toast.makeText(this, "Registration Failed : ${task.exception?.message}", Toast.LENGTH_LONG).show()


                            }
                        }
            )
        }
    }
}
