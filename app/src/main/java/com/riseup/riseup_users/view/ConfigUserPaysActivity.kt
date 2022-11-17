package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.riseup.riseup_users.view.fragments.LastPaymentsFragment
import com.riseup.riseup_users.R
import com.riseup.riseup_users.view.fragments.UserCreditCardsPaymentsFragment
import com.riseup.riseup_users.databinding.ActivityConfigUserPaysBinding

class ConfigUserPaysActivity : AppCompatActivity() {

    //private lateinit var navigator:BottomNavigationView

    private lateinit var binding: ActivityConfigUserPaysBinding
    private lateinit var paymentsFragment: LastPaymentsFragment
    private lateinit var userCreditCardsFragment: UserCreditCardsPaymentsFragment

    //para la lista del recyclerView de tarjetas
    //private lateinit var layoutManager: LinearLayoutManager
    //private lateinit var adapter: UserCardsPaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigUserPaysBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        paymentsFragment = LastPaymentsFragment.newInstance()
        userCreditCardsFragment = UserCreditCardsPaymentsFragment.newInstance()


        showFragment(paymentsFragment)

        binding.navSuperiorConfigUsersPays.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.menuTransaccionesItem){

                showFragment(paymentsFragment)
            } else if(menuItem.itemId == R.id.menuTarjetasItem){
                showFragment(userCreditCardsFragment)

            }
            true

        }

        binding.atrasUserConfigPaysBtn.setOnClickListener {
            startActivity(Intent(this@ConfigUserPaysActivity, ConfigurationActivity::class.java))
        }

    }

    fun showFragment(fragment: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentsConstrainLayoutPaymentsUser, fragment)
        transaction.commit()
    }
}