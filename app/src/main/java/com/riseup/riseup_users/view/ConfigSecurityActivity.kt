package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityConfigSecurityBinding

class ConfigSecurityActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigSecurityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigSecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.atrasBtnContrasenaConfig.setOnClickListener {
            startActivity(Intent(this@ConfigSecurityActivity, ConfigurationActivity::class.java))
        }
    }
}