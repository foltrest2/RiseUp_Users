package com.riseup.riseup_users.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.FinishPaymentBinding
import com.riseup.riseup_users.util.OrderDialog


class FinishPaymentActivity : AppCompatActivity() {
    private lateinit var binding : FinishPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  FinishPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showPUOrderProducts.setOnClickListener {

            OrderDialog().show(supportFragmentManager,"dialog")
        }
    }
}