package com.riseup.riseup_users.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.ActivityConfigDiamondHistorialBinding
import com.riseup.riseup_users.util.DiamondHistAdapter
import com.riseup.riseup_users.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_config_diamond_historial.*

class ConfigDiamondHistorialActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigDiamondHistorialBinding
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var adapter: DiamondHistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigDiamondHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)

        diamondHistorialRecycler.layoutManager = layoutManager
        diamondHistorialRecycler.setHasFixedSize(true)

        adapter = DiamondHistAdapter()
        diamondHistorialRecycler.adapter = adapter

    }
}