package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.riseup.riseup_users.view.fragments.LastPaymentsFragment
import com.riseup.riseup_users.R
import com.riseup.riseup_users.view.fragments.UserCreditCardsPaymentsFragment
import com.riseup.riseup_users.databinding.ActivityConfigUserPaysBinding
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.viewmodel.ConfigUserPaysViewModel

class ConfigUserPaysActivity : AppCompatActivity() {

    //private lateinit var navigator:BottomNavigationView

    private var _binding : ActivityConfigUserPaysBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : UserModel
    private val viewModel : ConfigUserPaysViewModel by viewModels()

    private lateinit var paymentsFragment: LastPaymentsFragment
    private lateinit var userCreditCardsFragment: UserCreditCardsPaymentsFragment


    //para la lista del recyclerView de tarjetas
    //private lateinit var layoutManager: LinearLayoutManager
    //private lateinit var adapter: UserCardsPaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityConfigUserPaysBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        user = loadUser()!!

        paymentsFragment = LastPaymentsFragment.newInstance()
        userCreditCardsFragment = UserCreditCardsPaymentsFragment.newInstance()
        viewModel.loadPayments(user)


        showFragment(paymentsFragment)
        binding.cardsDivider.setBackgroundResource(R.color.grayFigma)

        binding.navSuperiorConfigUsersPays.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.menuTransaccionesItem) {
                showFragment(paymentsFragment)
                binding.cardsDivider.setBackgroundResource(R.color.grayFigma)
                binding.transactionsDivider.setBackgroundResource(R.drawable.gradient_rosado_azul)
            } else if (menuItem.itemId == R.id.menuTarjetasItem){
                binding.transactionsDivider.setBackgroundResource(R.color.grayFigma)
                binding.cardsDivider.setBackgroundResource(R.drawable.gradient_rosado_azul)
                showFragment(userCreditCardsFragment)
            }

            true
        }

        binding.atrasUserConfigPaysBtn.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigUserPaysActivity, ConfigurationActivity::class.java))
        }

    }

    fun showFragment(fragment: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentsConstrainLayoutPaymentsUser, fragment)
        transaction.commit()
    }
    private fun loadUser(): UserModel? {
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = sp.getString("Usuario", "NO_USER")
        if (json == "NO_USER") {
            return null
        } else {
            return Gson().fromJson(json, UserModel::class.java)
        }
    }
}