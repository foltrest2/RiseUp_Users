package com.riseup.riseup_users.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.riseup.riseup_users.databinding.ActivityConfigProfileChangeSexBinding
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.viewmodel.ConfigProfileChangeSexViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ConfigProfileChangeSexActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigProfileChangeSexBinding
    private val viewModel : ConfigProfileChangeSexViewModel by viewModels()
    private var newSex : String = ""
    private lateinit var user : UserModel

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityConfigProfileChangeSexBinding.inflate(this.layoutInflater)
        this.setContentView(this.binding.root)

        val userFromExtra = intent.getStringExtra("user")!!
        user = Gson().fromJson(userFromExtra,UserModel::class.java)

        //Inicializacion del viewModel
        viewModel.setSpUser(user)

        //Listener de la modificacion
        viewModel.inComingUser.observe(this){
            Log.e(">>>", "Actualizado en observer: ${it}")
            saveUserSp(it)
        }

        binding.closeChangeUserSexBtn.setOnClickListener{
            finish()
            startActivity(Intent(this@ConfigProfileChangeSexActivity, ConfigProfileInfoActivity::class.java))
        }
        binding.changeUserSexAcceptBtn.setOnClickListener{
            //validateNewSex()
            GlobalScope.launch {
                if (newSex.isNotEmpty()){
                    viewModel.updateSex(newSex, user)
                } else if (binding.personalizadoET.text.toString().isNotEmpty()){
                    newSex = binding.personalizadoET.text.toString()
                    viewModel.updateSex(newSex, user)
                }
                finish()
                startActivity(Intent(this@ConfigProfileChangeSexActivity, ConfigProfileInfoActivity::class.java))
            }
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


    private fun saveUserSp(user: UserModel){
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario",json).apply()
    }

}