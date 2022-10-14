package com.riseup.riseup_users.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity
=======
>>>>>>> feb9055f1df054f2165f67b05ce89e926e0d98b7
import com.riseup.riseup_users.databinding.ActivityMainBinding
import com.riseup.riseup_users.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val switchActivityIntent = Intent(this, LoginActivity::class.java)
        switchActivityIntent.putExtra(
            "message",
            "From: " + LoginActivity::class.java.getSimpleName()
        )
        startActivity(switchActivityIntent)
    }
}