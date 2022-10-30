package com.riseup.riseup_users.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.riseup.riseup_users.databinding.ActivityRegisterBinding
import com.riseup.riseup_users.util.EmailAlreadyExistsDialog
import com.riseup.riseup_users.util.PassNotMatchDialog
import com.riseup.riseup_users.viewmodel.RegisterViewModel
import com.riseup.riseup_users.viewmodel.AuthResult
import java.util.*

class RegisterActivity : AppCompatActivity() {

    val viewmodel: RegisterViewModel by viewModels()

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel.authState.observe(this){
            when(it.result){
                AuthResult.IDLE ->{

                }
                AuthResult.SUCCESS->{
                    AuthResult.SUCCESS
                    Toast
                        .makeText(this, "Registrado con exito", Toast.LENGTH_LONG)
                        .show()
                    //startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                }
                AuthResult.FAIL->{
                   if(it.message == "RepeatedEmail"){

                       EmailAlreadyExistsDialog().show(supportFragmentManager,"emailAlreadyExistDialog")
                   }
                }
            }
        }

        // on below line we are adding
        // click listener for our edit text.
        binding.birthdayRegTF.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.birthdayRegTF.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.

            datePickerDialog.show()
        }
        /*

        binding.returnToLoginButton.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
        binding.regBtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MenuActivity::class.java))
        }
        binding.googleRegBtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MenuActivity::class.java))
        }
        binding.twRegBtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MenuActivity::class.java))
        }
        binding.fbRegBtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MenuActivity::class.java))
        }


        */

        binding.regBtn.setOnClickListener {

            var validate = viewmodel.validatePassword(binding.passwordRegTF.text.toString(),binding.confPassRegTF.text.toString())
            if(validate){
                    regBtnAction()
                }else{
                  PassNotMatchDialog().show(supportFragmentManager,"passnotmachDialog")
                }

        }

    }


    fun regBtnAction(){
              viewmodel.signUp(
                  binding.cellPhoneRegTF.text.toString(),
                  binding.emailRegTF.text.toString(),
                  0,
                  binding.birthdayRegTF.text.toString(),
                  "Colombia",
                  binding.nameRegTF.text.toString(),
                  binding.sexRegTF.text.toString(),
                  binding.passwordRegTF.text.toString()
              )


    }
}