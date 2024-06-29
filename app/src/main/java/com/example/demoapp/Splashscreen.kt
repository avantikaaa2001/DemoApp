package com.example.demoapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoapp.databinding.ActivitySplashscreenBinding

@SuppressLint("CustomSplashScreen")
class Splashscreen : AppCompatActivity() {
    private val binding :ActivitySplashscreenBinding by lazy {
        ActivitySplashscreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        //below is the code for connecting splash screen to login page
 Handler(Looper.getMainLooper()).postDelayed({
     startActivity(Intent(this,Signin::class.java))
 },3000)
    }
}