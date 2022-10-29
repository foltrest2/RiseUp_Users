package com.riseup.riseup_users.view

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.ActivityConfigProfileInfoBinding
import kotlinx.android.synthetic.main.activity_config_profile_info.*
import java.util.*
import java.util.Calendar.DAY_OF_MONTH

class ConfigProfileInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigProfileInfoBinding
    private lateinit var dateSelected : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigProfileInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dateSelected = ""


        binding.atrasBtnInfoP.setOnClickListener {
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigurationActivity::class.java))
        }

        binding.correoETConfigUser.setOnClickListener {
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeEmailActivity::class.java))
        }

        binding.telETConfigUser.setOnClickListener {
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeTelActivity::class.java))
        }

        binding.configETSexoUser.setOnClickListener{
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeSexActivity::class.java))
        }

        binding.acceptDateBtn.setOnClickListener {
            datePickerConstraint.visibility = View.GONE
        }

        binding.configETFechaUser.setOnClickListener {
            datePickerConstraint.visibility = View.VISIBLE

            val datePicker = binding.datePickerUserConfigBith
            val today = Calendar.getInstance()

            datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(DAY_OF_MONTH)){
                    view, year, month, day ->
                val month = month + 1
                val msg = "$day/$month/$year"
                replaceDate(msg)
            }
        }
    }
    fun replaceDate(newDate : String){
        binding.configETFechaUser.setText(newDate)
    }
}