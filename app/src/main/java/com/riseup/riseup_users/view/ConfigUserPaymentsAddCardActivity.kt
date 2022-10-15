package com.riseup.riseup_users.view

import android.content.Intent
import com.riseup.riseup_users.databinding.ActivityConfigUserPaymentsAddCardBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ConfigUserPaymentsAddCardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigUserPaymentsAddCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigUserPaymentsAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtnAddCardUserPay.setOnClickListener {
            startActivity(Intent(this@ConfigUserPaymentsAddCardActivity, ConfigUserPaysActivity::class.java))
        }
    }
}