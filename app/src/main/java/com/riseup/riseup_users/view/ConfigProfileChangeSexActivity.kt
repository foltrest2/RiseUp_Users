package com.riseup.riseup_users.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.riseup.riseup_users.databinding.ActivityConfigProfileChangeSexBinding


class ConfigProfileChangeSexActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigProfileChangeSexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityConfigProfileChangeSexBinding.inflate(this.layoutInflater)
        this.setContentView(this.binding.root)

        this.binding.closeChangeUserSexBtn.setOnClickListener{
            this.startActivity(Intent(this@ConfigProfileChangeSexActivity, ConfigProfileInfoActivity::class.java))
        }
        this.binding.changeUserSexAcceptBtn.setOnClickListener{
            this.startActivity(Intent(this@ConfigProfileChangeSexActivity, ConfigurationActivity::class.java))
        }
        this.binding.personalizadoRB.setOnCheckedChangeListener { buttonView, isChecked ->
            this.showPersonalized(isChecked)
        }
    }

    fun showPersonalized(isChecked: Boolean) {
        if (isChecked){
            this.binding.personalizadoET.visibility = View.VISIBLE
        } else if(!isChecked){
            this.binding.personalizadoET.visibility = View.GONE
        }

    }

}