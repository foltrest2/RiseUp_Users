package com.riseup.riseup_users.view

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.riseup.riseup_users.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(){

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userLoginBtn.setOnClickListener {
            val switchActivityIntent = Intent(this,MenuActivity::class.java).apply {
                putExtra("principalFragment","principalFragment")
            }
            startActivity(switchActivityIntent)
        }

        binding.userRegBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        //createTextGradient()
    }


    /*fun createTextGradient() {
        val paint = binding.textView.paint
        val width = paint.measureText(binding.textView.text.toString())
        val textShader: Shader = LinearGradient(
            0f, 0f, width, binding.textView.textSize, intArrayOf(
                Color.parseColor("#8000F5A0"),
                Color.parseColor("#8000D9F5"),
            ), null, Shader.TileMode.REPEAT
        )
        paint.setShader(null);
        paint.setShadowLayer(180f, 5f, 5f, Color.WHITE);
        paint.clearShadowLayer();
        binding.textView.paint.setShader(textShader)
    }
*/




}



