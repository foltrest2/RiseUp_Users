package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityLoginBinding
import com.riseup.riseup_users.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnToLoginButton.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
        binding.regBtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MenuActivity::class.java))
        }
        binding.googleRegBtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MenuActivity::class.java))
        }
        binding.twRegBtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MenuActivity::class.java))
        }
        binding.fbRegBtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MenuActivity::class.java))
        }
    }
}