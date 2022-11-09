package com.riseup.riseup_users.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.riseup.riseup_users.databinding.ActivityConfigProfileInfoBinding
import com.riseup.riseup_users.model.User
import com.riseup.riseup_users.viewmodel.ConfigProfileInfoViewModel
import kotlinx.android.synthetic.main.activity_config_profile_info.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DAY_OF_MONTH

class ConfigProfileInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigProfileInfoBinding
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var user : User
    private val viewModel: ConfigProfileInfoViewModel by viewModels()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigProfileInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicilizacion del usuario desde los sp
        user = loadUser()!!
        loadUserInfo(user)

        //Inicializacion del viewModel
        viewModel.setBinding(binding)
        viewModel.setSpUser(user)

        //Listener de la modificacion
        viewModel.inComingUser.observe(this){
            Log.e(">>>", "Actualizado en observer: ${it}")
            saveUserSp(it)
        }

        viewModel.updateImage(user)
        //Glide.with(binding.profileInfoPImg).load(image).into(binding.profileInfoPImg)



        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::onGalleryResult
        )

        binding.atrasBtnInfoP.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigProfileInfoActivity, ConfigurationActivity::class.java))
        }

        binding.correoETConfigUser.setOnClickListener {
            startActivity(
                Intent(
                    this@ConfigProfileInfoActivity,
                    ConfigProfileChangeEmailActivity::class.java
                )
            )
        }

        binding.telETConfigUser.setOnClickListener {
            finish()
            val json = Gson().toJson(user)
            startActivity(
                Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeTelActivity::class.java)
                    .putExtra("user", json)
            )
        }

        binding.configETSexoUser.setOnClickListener {
            finish()
            val json = Gson().toJson(user)
            startActivity(
                Intent(this@ConfigProfileInfoActivity, ConfigProfileChangeSexActivity::class.java)
                    .putExtra("user", json)
            )
        }

        binding.acceptDateBtn.setOnClickListener {
            val dateET = binding.configETFechaUser.text.toString()
            val dateAtr = dateET.split("/")
            val day = dateAtr[0].toInt()
            val month = dateAtr[1].toInt() - 1
            val year = dateAtr[2].toInt()

            updateDateFirestore(year, month, day, user)
            datePickerConstraint.visibility = View.GONE
        }

        binding.configETFechaUser.setOnClickListener {
            datePickerConstraint.visibility = View.VISIBLE

            val datePicker = binding.datePickerUserConfigBith
            val today = Calendar.getInstance()

            datePicker.init(
                today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(DAY_OF_MONTH)
            ) { view, year, month, day ->
                val month = month + 1
                val msg = "$day/$month/$year"
                replaceDate(msg)
            }
        }

        binding.profileInfoPImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }
    }

    fun replaceDate(newDate: String) {
        binding.configETFechaUser.setText(newDate)
    }

    private fun loadUser(): User? {
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = sp.getString("Usuario", "NO_USER")
        if (json == "NO_USER") {
            return null
        } else {
            return Gson().fromJson(json, User::class.java)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadUserInfo(user: User) {
        binding.userNameProfileConfig.text = user.name
        binding.correoETConfigUser.hint = user.email
        binding.telETConfigUser.setText(user.cel)
        binding.configETSexoUser.setText(user.sex)
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.format(user.birth!!).toString()
        binding.configETFechaUser.setText(date)
    }

    private fun saveUserSp(user: User) {
        val sp = getSharedPreferences("RiseUpUser", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario", json).apply()
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateDateFirestore(year: Int, month: Int, day: Int, user: User) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, 6, 0, 0)
        val sdf = SimpleDateFormat("dd-MM-yyyy 'T' HH:mm:ss")
        val formatedDate = sdf.format(calendar.time)
        val date = sdf.parse(formatedDate)
        viewModel.changeDate(user, date!!)
        //user.birth = date
        //saveUserSp(user)
    }

    fun onGalleryResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val uriImage = result.data?.data
            binding.profileInfoPImg.setImageURI(uriImage)

            //Upload
            viewModel.uploadProfileImg(user, uriImage!!)
            //user.profileImg = filename
            //saveUserSp(user)
        }
    }
}


