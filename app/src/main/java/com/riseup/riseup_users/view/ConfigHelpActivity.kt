package com.riseup.riseup_users.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import com.riseup.riseup_users.databinding.ActivityConfigHelpBinding
import com.riseup.riseup_users.viewmodel.ConfigHelpViewModel

class ConfigHelpActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigHelpBinding
    private val viewModel : ConfigHelpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.atrasArrowHelpBtn.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigHelpActivity, ConfigurationActivity::class.java))
        }

        binding.soporteYpreguntasFreqConstraint.setOnClickListener {
            startActivity(Intent(this@ConfigHelpActivity, ConfigHelpCenterActivity::class.java))
        }

        binding.reportarProbConstraint.setOnClickListener {
            BugReportDialog(
                onSubmitClickListener = { report ->
                    if(report.isNotEmpty()){
                        viewModel.sendReport(report)
                    } else {
                        Toast.makeText(this,"Porfavor especifique la falla", Toast.LENGTH_LONG).show()
                    }
                }
            ).show(supportFragmentManager, "dialog")
        }
    }
}