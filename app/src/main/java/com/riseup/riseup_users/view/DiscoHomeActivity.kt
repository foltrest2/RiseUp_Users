package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityDiscoHomeBinding

class DiscoHomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDiscoHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityDiscoHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewAlcoholMenuDiscoSelected.setOnClickListener {
            val switchActivityIntent = Intent(this,MenuActivity::class.java)
            switchActivityIntent.putExtra("menuLicoresFragment","menuLicoresFragment")
            startActivity(switchActivityIntent)
        }
    }

}