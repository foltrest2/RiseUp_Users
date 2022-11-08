package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.riseup.riseup_users.databinding.ActivityConfigChangePasswordVerifBinding

class ConfigProfileChangePassVerifActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigChangePasswordVerifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigChangePasswordVerifBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backArrowChangePassConfigUserVerifi.setOnClickListener {
            startActivity(Intent(this@ConfigProfileChangePassVerifActivity, ConfigSecurityActivity::class.java))
        }

        binding.verifyChangePassCodeBtn.setOnClickListener {
            Toast.makeText(this,"Código validado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@ConfigProfileChangePassVerifActivity, ConfigurationActivity::class.java))
        }

        binding.resendVerifCodeChangePassTV.setOnClickListener {
            Toast.makeText(this,"Reenviando código", Toast.LENGTH_SHORT).show()
        }

    }
}