package com.riseup.riseup_users.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.text.trimmedLength
import com.google.gson.Gson
import com.riseup.riseup_users.databinding.ActivityConfigProfileChangeTelBinding
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.viewmodel.ConfigProfileChangeTelViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConfigProfileChangeTelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigProfileChangeTelBinding
    private val viewModel : ConfigProfileChangeTelViewModel by viewModels()
    private lateinit var user : UserModel

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigProfileChangeTelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userFromExtra = intent.getStringExtra("user")!!
        user = Gson().fromJson(userFromExtra,UserModel::class.java)

        //Inicializacion del viewModel
        viewModel.setSpUser(user)

        //Listener de la modificacion
        viewModel.inComingUser.observe(this){
            Log.e(">>>", "Actualizado en observer: ${it}")
            saveUserSp(it)
        }

        binding.cancelChangeTelBtn1.setOnClickListener {
            startActivity(Intent(this@ConfigProfileChangeTelActivity, ConfigProfileInfoActivity::class.java))
            finish()
        }

        binding.confirmarCambioTelBtn.setOnClickListener {
            if(binding.entryNewTelET.text.isNotEmpty() && (binding.entryNewTelET.text.toString().trimmedLength() >= 10)){
                val newCel = binding.entryNewTelET.text.toString()
                //Hasta que el user en viewModel no sea modificado, no cierro
                GlobalScope.launch {
                    viewModel.changeTel(user,newCel)
                    finish()
                    startActivity(Intent(this@ConfigProfileChangeTelActivity, ConfigProfileInfoActivity::class.java))
                }
                //user.cel = newCel
                //saveUserSp(use)
                //startActivity(Intent(this@ConfigProfileChangeTelActivity, ConfigProfileInfoActivity::class.java))
            } else {
                Toast.makeText(this,"Proporcione un numero nuevo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUserSp(user: UserModel){
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario",json).apply()
    }

}