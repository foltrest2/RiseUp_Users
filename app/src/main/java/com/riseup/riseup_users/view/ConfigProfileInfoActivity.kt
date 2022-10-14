package com.riseup.riseup_users.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityConfigProfileInfoBinding

class ConfigProfileInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigProfileInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigProfileInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}