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
    private lateinit var principalFragment: PrincipalFragment
    private lateinit var diamondPaymentFragment: DiamondsPaymentFragment
    private lateinit var paymentCodeFragment: PaymentCodeFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        newPaymentSelectionFragment = PaymentSelectionFragment.newInstance()
        shoppingCarFragment = ShoppingCarFragment.newInstance()
        productListFragment = ProductListFragment.newInstance()
        principalFragment = PrincipalFragment.newInstance()
        diamondPaymentFragment = DiamondsPaymentFragment.newInstance()
        paymentCodeFragment = PaymentCodeFragment.newInstance()

        if(intent.extras?.isEmpty == true){
            showFragment(principalFragment)
        }else {
            val intentFragment = intent.extras?.get("principalFragment")
            if (intentFragment != null) {
                when (intentFragment) {
                    "principalFragment" -> showFragment(principalFragment)
                }
            }

            val intentFragment2 = intent.extras?.getString("menuLicoresFragment")
            if (intentFragment2 != null) {
                when (intentFragment2) {
                    "menuLicoresFragment" -> showFragment(productListFragment)
                }
            }
            val intentFragment3 = intent.extras?.getString("PrincipalFragment")
            if (intentFragment3 != null) {
                when (intentFragment3) {
                    "PrincipalFragment" -> showFragment(principalFragment)

                }
            }
        }

        binding.menuApp.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.homeOpMenu){
                showFragment(principalFragment)
            }else if(menuItem.itemId == R.id.carOpMenu){
                showFragment(shoppingCarFragment)
            }else if(menuItem.itemId == R.id.configmOpMenu){
                val switchActivityIntent = Intent(this@MenuActivity, ConfigurationActivity::class.java)
                startActivity(switchActivityIntent)
            }
            true
        }

    }
    private fun showFragment(fragment: Fragment){
            val transaction = supportFragmentManager.beginTransaction()
             transaction.replace(R.id.fragmentContainer, fragment)
             transaction.commit()
    }
}