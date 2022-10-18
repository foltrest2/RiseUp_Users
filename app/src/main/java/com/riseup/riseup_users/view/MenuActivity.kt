package com.riseup.riseup_users.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.riseup.riseup_users.*
import com.riseup.riseup_users.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMenuBinding
    private lateinit var newPaymentSelectionFragment: PaymentSelectionFragment
    private lateinit var shoppingCarFragment: ShoppingCarFragment
    private lateinit var productListFragment: ProductListFragment
    private lateinit var paymentCodeFragment: PaymentCodeFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        newPaymentSelectionFragment = PaymentSelectionFragment.newInstance()
        shoppingCarFragment = ShoppingCarFragment.newInstance()
        productListFragment = ProductListFragment.newInstance()
        paymentCodeFragment = PaymentCodeFragment.newInstance()
        showFragment(newPaymentSelectionFragment)

        val intentFragment = intent.extras?.getString("PaymentSelection")
        if(intentFragment!=null){
            when(intentFragment){

                "PaymentSelection"-> showFragment(newPaymentSelectionFragment)

            }
        }


        binding.menuApp.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.homeOpMenu){
                showFragment(shoppingCarFragment)

            }else if(menuItem.itemId == R.id.carOpMenu){

                showFragment(shoppingCarFragment)

            }else if(menuItem.itemId == R.id.configmOpMenu){

                val switchActivityIntent = Intent(this, ConfigurationActivity::class.java)
                startActivity(switchActivityIntent)
            }
            true
        }

    }
    fun showFragment(fragment: Fragment){
            val transaction = supportFragmentManager.beginTransaction()
             transaction.replace(R.id.fragmentContainer, fragment)
             transaction.commit()
    }
}