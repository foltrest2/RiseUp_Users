package com.riseup.riseup_users.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.ActivityDiscoSelectionBinding
import com.riseup.riseup_users.databinding.ActivityMainBinding

class DiscoSelectionActivity : AppCompatActivity() {

    val binding: ActivityDiscoSelectionBinding by lazy {
        ActivityDiscoSelectionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


}