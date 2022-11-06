package com.riseup.riseup_users.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.riseup.riseup_users.databinding.ActivityConfigProfileChangeSexBinding
import com.riseup.riseup_users.model.Usuario
import com.riseup.riseup_users.viewmodel.ConfigProfileChangeSexViewModel


class ConfigProfileChangeSexActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigProfileChangeSexBinding
    private val viewModel : ConfigProfileChangeSexViewModel by viewModels()
    private var newSex : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityConfigProfileChangeSexBinding.inflate(this.layoutInflater)
        this.setContentView(this.binding.root)

        binding.closeChangeUserSexBtn.setOnClickListener{
            this.startActivity(Intent(this@ConfigProfileChangeSexActivity, ConfigProfileInfoActivity::class.java))
        }
        binding.changeUserSexAcceptBtn.setOnClickListener{
            validateNewSex()
            this.startActivity(Intent(this@ConfigProfileChangeSexActivity, ConfigProfileInfoActivity::class.java))
        }
        binding.personalizadoRB.setOnCheckedChangeListener { buttonView, isChecked ->
            showPersonalized(isChecked)
        }
        binding.sexOptionsRadioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            //var newSex : String
            if(binding.hombreRB.id == checkedId){
                newSex = binding.hombreRB.text.toString()
            }

            if(binding.mujerRB.id == checkedId){
                newSex = binding.mujerRB.text.toString()
            }

            if(binding.noDecirSexoRB.id == checkedId){
                newSex = binding.noDecirSexoRB.text.toString()

            }
        }
    }

    private fun showPersonalized(isChecked: Boolean) {
        if (isChecked){
            binding.personalizadoET.visibility = View.VISIBLE
        } else if(!isChecked){
            binding.personalizadoET.visibility = View.GONE
        }
    }

    private fun validateNewSex(){
        val user = intent.getStringExtra("user")!!
        val userToChange = Gson().fromJson(user,Usuario::class.java)

        if (newSex.isNotEmpty()){
            viewModel.updateSex(newSex, userToChange)
            userToChange.sexo = newSex
            saveUserSp(userToChange)
        } else if (binding.personalizadoET.text.toString().isNotEmpty()){
            newSex = binding.personalizadoET.text.toString()
            viewModel.updateSex(newSex, userToChange)
            userToChange.sexo = newSex
            saveUserSp(userToChange)
        }
    }

    private fun saveUserSp(user: Usuario){
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario",json).apply()
    }

}