package com.riseup.riseup_users.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.auth.User
import com.google.gson.Gson
import com.riseup.riseup_users.*
import com.riseup.riseup_users.databinding.ActivityMenuBinding
import com.riseup.riseup_users.model.Usuario
import com.riseup.riseup_users.viewmodel.MenuViewModel

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var newPaymentSelectionFragment: PaymentSelectionFragment
    private lateinit var shoppingCarFragment: ShoppingCarFragment
    private lateinit var productListFragment: ProductListFragment
    private lateinit var principalFragment: PrincipalFragment
    private lateinit var diamondPaymentFragment: DiamondsPaymentFragment
    private lateinit var paymentCodeFragment: PaymentCodeFragment
    private lateinit var discoHomeFragment: DiscoHomeFragment
    private val viewModel : MenuViewModel by viewModels()
    private lateinit var user: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Cargar el usuario de los sp
        val user = loadUser()
        if(viewModel.isNullUser(user)){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
            } else {
                this.user = user!!
                Toast.makeText(this, "Este usuario: ${this.user}", Toast.LENGTH_LONG).show()
        }


        newPaymentSelectionFragment = PaymentSelectionFragment.newInstance()
        shoppingCarFragment = ShoppingCarFragment.newInstance()
        productListFragment = ProductListFragment.newInstance()
        principalFragment = PrincipalFragment.newInstance()
        diamondPaymentFragment = DiamondsPaymentFragment.newInstance()
        paymentCodeFragment = PaymentCodeFragment.newInstance()
        discoHomeFragment = DiscoHomeFragment.newInstance()


        showFragment(principalFragment)



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
        val intentFragment3 = intent.extras?.getString("discoHomeFragment")
        if (intentFragment3 != null) {
            when (intentFragment3) {
                "discoHomeFragment" -> showFragment(discoHomeFragment)
            }
        }
        val intentFragment4 = intent.extras?.getString("Login")
        if (intentFragment4 != null) {
          Toast.makeText(this,intent.extras?.get("Login").toString(),Toast.LENGTH_LONG).show()
        }

        binding.menuApp.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.homeOpMenu) {
                showFragment(principalFragment)
            } else if (menuItem.itemId == R.id.carOpMenu) {
                showFragment(shoppingCarFragment)
            } else if (menuItem.itemId == R.id.configmOpMenu) {
                val switchActivityIntent =
                    Intent(this@MenuActivity, ConfigurationActivity::class.java)
                startActivity(switchActivityIntent)
            }
            true
        }

    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    private fun loadUser():Usuario?{
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = sp.getString("Usuario", "NO_USER")
        if(json == "NO_USER"){
            return null
        }else{
            return Gson().fromJson(json, Usuario::class.java)
        }
    }
}