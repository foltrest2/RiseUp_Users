package com.riseup.riseup_users.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.riseup.riseup_users.databinding.ActivityConfigProfileInfoBinding
import com.riseup.riseup_users.model.Usuario
import com.riseup.riseup_users.viewmodel.ConfigProfileInfoViewModel
import kotlinx.android.synthetic.main.activity_config_profile_info.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DAY_OF_MONTH

class ConfigProfileInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigProfileInfoBinding

    private val viewModel : ConfigProfileInfoViewModel by viewModels()


    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigProfileInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = loadUser()
        loadUserInfo(user!!)

        binding.atrasBtnInfoP.setOnClickListener {
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigurationActivity::class.java))
        }

        binding.correoETConfigUser.setOnClickListener {
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeEmailActivity::class.java))
        }

        binding.telETConfigUser.setOnClickListener {
            finish()
            val json = Gson().toJson(user)
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeTelActivity::class.java)
                .putExtra("user",json))
        }

        binding.configETSexoUser.setOnClickListener{
            finish()
            val json = Gson().toJson(user)
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeSexActivity::class.java)
                .putExtra("user",json))
        }

        binding.acceptDateBtn.setOnClickListener {
            val dateET = binding.configETFechaUser.text.toString()
            val dateAtr = dateET.split("/")
            val day = dateAtr[0].toInt()
            val month = dateAtr[1].toInt()-1
            val year = dateAtr[2].toInt()

            updateDateFirestore(year, month, day, user)
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
    private fun loadUser():Usuario?{
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = sp.getString("Usuario", "NO_USER")
        if(json == "NO_USER"){
            return null
        }else{
            return Gson().fromJson(json, Usuario::class.java)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadUserInfo(user : Usuario){
        binding.userNameProfileConfig.text = user.nombre
        binding.correoETConfigUser.hint = user.correo
        binding.telETConfigUser.setText(user.celular)
        binding.configETSexoUser.setText(user.sexo)
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.format(user.nacimiento!!).toString()
        binding.configETFechaUser.setText(date)
    }

    private fun saveUserSp(user: Usuario){
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario",json).apply()
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateDateFirestore(year : Int, month : Int, day : Int, user : Usuario){
        val calendar = Calendar.getInstance()
        calendar.set(year,month,day,6,0,0)
        val sdf = SimpleDateFormat("dd-MM-yyyy 'T' HH:mm:ss")
        val formatedDate = sdf.format(calendar.time)
        val date = sdf.parse(formatedDate)
        viewModel.changeDate(user, date!!)
        user.nacimiento = date
        saveUserSp(user)
    }
}