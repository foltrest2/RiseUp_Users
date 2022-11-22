package com.riseup.riseup_users.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.riseup.riseup_users.databinding.ActivityLoginBinding
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.ErrorDialog
import com.riseup.riseup_users.util.SuccessfulRegisterDialog
import com.riseup.riseup_users.viewmodel.AuthResult
import com.riseup.riseup_users.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity(){

    private lateinit var binding: ActivityLoginBinding
    val viewmodel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.extras?.get("Dialog")
        if (intent != null) {
            when (intent) {
                "showDialog" -> showDialog()
            }
        }

        viewmodel.authState.observe(this){
            when(it.result){
                AuthResult.IDLE ->{
                }
                AuthResult.SUCCESS->{
                    when(it.message){

                        "NotVerified"-> {
                            val dialogFragmentP = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","NotVerified")
                            dialogFragmentP.arguments = bundle
                            dialogFragmentP.show(supportFragmentManager,"notVerifiedDialog")

                        }
                        "SuccessAndVerified"->{
                            val thisUserToSave = viewmodel.saveUserFromViewModel()
                            saveUser(thisUserToSave)
                            Log.e(">>>", "SAVED: $thisUserToSave")
                            startActivity(Intent(this@LoginActivity, MenuActivity::class.java))
                            finish()
                        }
                    }
                }
                AuthResult.FAIL->{

                    when(it.message){
                        "wrongPassword"-> {
                            val dialogFragmentP = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","WrongPassword")
                            dialogFragmentP.arguments = bundle
                            dialogFragmentP.show(supportFragmentManager,"wrongPasswordDialog")
                        }
                        "invalidEmail"->{
                            val dialogFragmentE = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","InvalidEmail")
                            dialogFragmentE.arguments = bundle
                            dialogFragmentE.show(supportFragmentManager,"invalidEmailDialog")
                        }
                        "userNotFound"->{
                            val dialogFragmentU = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","UserNotFound")
                            dialogFragmentU.arguments = bundle
                            dialogFragmentU.show(supportFragmentManager,"userNotFoundDialog")
                        }
                        "networkError"->{
                            val dialogFragmentU = ErrorDialog()
                            val bundle = Bundle()
                            bundle.putString("TEXT","NetworkError")
                            dialogFragmentU.arguments = bundle
                            dialogFragmentU.show(supportFragmentManager,"networkError")
                        }
                    }
                    /*
                    if(it.message == "wrongPassword"){

                        dbinding.passNotMatchdescTV.text = "hola"
                        EmailAlreadyExistsDialog().show(supportFragmentManager,"emailAlreadyExistDialog")

                    }


                     */
                }
            }
        }
        binding.userLoginBtn.setOnClickListener {
            logIn()
            /*val switchActivityIntent = Intent(this,MenuActivity::class.java).apply {
                putExtra("PrincipalFragment","PrincipalFragment")
            val switchActivityIntent = Intent(this,MenuActivity::class.java).apply {
            }
            startActivity(switchActivityIntent)

            */

        }

        binding.userRegBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        //createTextGradient()
    }
    fun logIn(){
        if(binding.emailLoginTF.text.isEmpty() || binding.loginPasswordTF.text.isEmpty() ){

            val dialogFragmentE = ErrorDialog()
            val bundle = Bundle()
            bundle.putString("TEXT","EmptyFields")
            dialogFragmentE.arguments = bundle
            dialogFragmentE.show(supportFragmentManager,"EmptyFieldsDialog")
        }else{
            viewmodel.signIn(binding.emailLoginTF.text.toString(),binding.loginPasswordTF.text.toString())

        }

    }

    fun showDialog(){
        SuccessfulRegisterDialog().show(supportFragmentManager,"successfullyRegister")
    }

    private fun saveUser(user:UserModel){
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario",json).apply()
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



