package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityConfigProfileInfoBinding

class ConfigProfileInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigProfileInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigProfileInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.atrasBtnInfoP.setOnClickListener {
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigurationActivity::class.java))
        }

        binding.correoETConfigUser.setOnClickListener {
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeEmailActivity::class.java))
        }

        binding.telETConfigUser.setOnClickListener {
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeTelActivity::class.java))
        }

    }
}