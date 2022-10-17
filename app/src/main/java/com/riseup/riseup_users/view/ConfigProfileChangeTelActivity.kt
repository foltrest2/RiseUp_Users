package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityConfigProfileChangeTelBinding

class ConfigProfileChangeTelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigProfileChangeTelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigProfileChangeTelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelChangeTelBtn1.setOnClickListener {
            startActivity(Intent(this@ConfigProfileChangeTelActivity, ConfigProfileInfoActivity::class.java))
        }

        binding.confirmarCambioTelBtn.setOnClickListener {
            startActivity(Intent(this@ConfigProfileChangeTelActivity, ConfigurationActivity::class.java))
        }

    }

}