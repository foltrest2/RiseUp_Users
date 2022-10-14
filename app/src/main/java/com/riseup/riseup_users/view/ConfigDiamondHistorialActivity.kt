package com.riseup.riseup_users.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityConfigDiamondHistorialBinding

class ConfigDiamondHistorialActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigDiamondHistorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigDiamondHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}