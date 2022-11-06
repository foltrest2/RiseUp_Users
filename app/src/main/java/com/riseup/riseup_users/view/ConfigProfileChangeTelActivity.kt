package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.text.trimmedLength
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.riseup.riseup_users.databinding.ActivityConfigProfileChangeTelBinding
import com.riseup.riseup_users.model.Usuario
import com.riseup.riseup_users.viewmodel.ConfigProfileChangeTelViewModel

class ConfigProfileChangeTelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigProfileChangeTelBinding
    private val viewModel : ConfigProfileChangeTelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigProfileChangeTelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelChangeTelBtn1.setOnClickListener {
            startActivity(Intent(this@ConfigProfileChangeTelActivity, ConfigProfileInfoActivity::class.java))
            finish()
        }

        binding.confirmarCambioTelBtn.setOnClickListener {
            if(binding.entryNewTelET.text.isNotEmpty() && (binding.entryNewTelET.text.toString().trimmedLength() >= 10)){
                finish()
                val user = intent.getStringExtra("user")!!
                val userToChange = Gson().fromJson(user,Usuario::class.java)
                val newCel = binding.entryNewTelET.text.toString()
                viewModel.changeTel(userToChange,newCel)
                userToChange.celular = newCel
                saveUserSp(userToChange)
                startActivity(Intent(this@ConfigProfileChangeTelActivity, ConfigProfileInfoActivity::class.java))
            } else {
                Toast.makeText(this,"Proporcione un numero nuevo", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveUserSp(user: Usuario){
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario",json).apply()
    }

}