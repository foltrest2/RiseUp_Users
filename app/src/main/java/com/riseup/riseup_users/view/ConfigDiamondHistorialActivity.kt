package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.riseup.riseup_users.databinding.ActivityConfigDiamondHistorialBinding
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.DiamondHistAdapter
import com.riseup.riseup_users.viewmodel.DiamondHistorialViewModel
import com.riseup.riseup_users.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_config_diamond_historial.*

class ConfigDiamondHistorialActivity : AppCompatActivity() {

    private var _binding : ActivityConfigDiamondHistorialBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : UserModel

    private val viewModel : DiamondHistorialViewModel by viewModels()

    private val adapter = DiamondHistAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityConfigDiamondHistorialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        user = loadUser()!!

        var cardsRecycler = binding.diamondHistorialRecycler
        cardsRecycler.setHasFixedSize(true)
        cardsRecycler.layoutManager = LinearLayoutManager(this)
        cardsRecycler.adapter = adapter

        diamondHistorialRecycler.adapter = adapter
        viewModel.loadTransactions(user)

        viewModel.transactions.observe(this){
            if(it.isNotEmpty()) {
                adapter.clear()
                for (transaction in it) {
                    adapter.addHistory(transaction)
                }
            }
        }

        binding.atrasHistTrans.setOnClickListener {
            startActivity(Intent(this@ConfigDiamondHistorialActivity, ConfigurationActivity::class.java))
        }

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