package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityConfigHelpBinding

class ConfigHelpActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.atrasArrowHelpBtn.setOnClickListener {
            startActivity(Intent(this@ConfigHelpActivity, ConfigurationActivity::class.java))
        }

    }
}