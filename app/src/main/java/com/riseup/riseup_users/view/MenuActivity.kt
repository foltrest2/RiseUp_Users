package com.riseup.riseup_users.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.riseup.riseup_users.PaymentSelectionFragment
import com.riseup.riseup_users.R
import com.riseup.riseup_users.ShoppingCarFragment
import com.riseup.riseup_users.databinding.ActivityMenuBinding
import com.riseup.riseup_users.ProductListFragment

class MenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMenuBinding
    private lateinit var newPaymentSelectionFragment: PaymentSelectionFragment
    private lateinit var shoppingCarFragment: ShoppingCarFragment
    private lateinit var productListFragment: ProductListFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        newPaymentSelectionFragment = PaymentSelectionFragment.newInstance()
        shoppingCarFragment = ShoppingCarFragment.newInstance()
        productListFragment = ProductListFragment.newInstance()
        showFragment(newPaymentSelectionFragment)

        binding.menuApp.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.homeOpMenu){
            }else if(menuItem.itemId == R.id.carOpMenu){
                //showFragment(shoppingCarFragment)
                showFragment(productListFragment)

            }else if(menuItem.itemId == R.id.configmOpMenu){
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