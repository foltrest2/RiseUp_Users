package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.get
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.ActivityConfigurationBinding
import kotlinx.android.synthetic.main.activity_configuration.view.*
import kotlinx.android.synthetic.main.cache_cleaned.view.*

class ConfigurationActivity : AppCompatActivity() {


    private lateinit var binding: ActivityConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.constraintLayoutConfigSuperior.atrasBtnConfigMain.setOnClickListener {
            startActivity(Intent(this@ConfigurationActivity, MenuActivity::class.java))
        }
        binding.scLinearLayoutConfig.contraintInfoPersonalGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigProfileInfoActivity::class.java))
        }
        binding.scLinearLayoutConfig.contraintHistDiamantesGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigDiamondHistorialActivity::class.java))
        }
        binding.scLinearLayoutConfig.contraintPrivYSeguridadGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigSecurityActivity::class.java))
        }
        binding.scLinearLayoutConfig.contraintUserPagosGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigUserPaysActivity::class.java))
        }
        binding.scLinearLayoutConfig.contraintIdiomaGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigLanguageActivity::class.java))
        }
        binding.scLinearLayoutConfig.contraintInfoAppGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigInformationActivity::class.java))
        }
        binding.scLinearLayoutConfig.contraintHelpAppGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigHelpActivity::class.java))
        }
        binding.scLinearLayoutConfig.logOutBtnProfile.setOnClickListener {
            startActivity(Intent(this@ConfigurationActivity, LoginActivity::class.java))
        }
        binding.scLinearLayoutConfig.borrarCacheTVProfile.setOnClickListener {
            CacheCleanedDialog().show(supportFragmentManager, "dialogcache")
        }
    }
}