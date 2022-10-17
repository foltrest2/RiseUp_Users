package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityConfigInformationBinding

class ConfigInformationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.atrasArrowInfoAppBtn.setOnClickListener {
            startActivity(Intent(this@ConfigInformationActivity, ConfigurationActivity::class.java))
        }
        binding.politicaPrivConstraint.setOnClickListener {
            startActivity(Intent(this@ConfigInformationActivity, ConfigPoliticInformationActivity::class.java))
        }
    }
}