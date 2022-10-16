package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_users.databinding.ActivityConfigProfileChangeEmailBinding

class ConfigProfileChangeEmailActivity : AppCompatActivity() {

    private lateinit var bindig : ActivityConfigProfileChangeEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityConfigProfileChangeEmailBinding.inflate(layoutInflater)
        setContentView(bindig.root)

        bindig.closeChangeEmailBtn.setOnClickListener {
            startActivity(Intent(this@ConfigProfileChangeEmailActivity, ConfigProfileInfoActivity::class.java))
        }
        bindig.checkChangeEmailBtn.setOnClickListener {
            startActivity(Intent(this@ConfigProfileChangeEmailActivity, ConfigurationActivity::class.java))
        }
    }
}