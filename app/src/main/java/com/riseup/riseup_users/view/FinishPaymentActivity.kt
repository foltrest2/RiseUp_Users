package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.PaymentSelectionFragment
import com.riseup.riseup_users.ShoppingCarFragment
import com.riseup.riseup_users.databinding.FinishPaymentBinding
import com.riseup.riseup_users.util.OrderDialog


class FinishPaymentActivity : AppCompatActivity() {
    private lateinit var menuActivity: MenuActivity
    private lateinit var shoppingCarFragment: ShoppingCarFragment
    private lateinit var binding : FinishPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  FinishPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.confirmPaymentBtnMain.setOnClickListener {


        }
        binding.reOrderBtnMain.setOnClickListener {


            shoppingCarFragment = ShoppingCarFragment.newInstance()
            menuActivity= MenuActivity()

            val switchActivityIntent = Intent(this,MenuActivity::class.java)
            switchActivityIntent.putExtra("PaymentSelection","PaymentSelection")
            startActivity(switchActivityIntent)

        }

        binding.showPUOrderProducts.setOnClickListener {

            OrderDialog().show(supportFragmentManager,"dialog")
        }
    }
}