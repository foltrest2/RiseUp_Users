package com.riseup.riseup_users.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_users.databinding.FinishPaymentBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.model.TransactionModel
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.CodeGenerator
import com.riseup.riseup_users.util.OrderDialog
import com.riseup.riseup_users.util.ProductsShoppingCarAdapter
import com.riseup.riseup_users.view.fragments.ShoppingCarFragment
import com.riseup.riseup_users.viewmodel.FinishPaymentViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FinishPaymentActivity : AppCompatActivity() {
    private lateinit var menuActivity: MenuActivity
    private lateinit var shoppingCarFragment: ShoppingCarFragment
    private lateinit var binding: FinishPaymentBinding
    private val adapter = ProductsShoppingCarAdapter()
    private val viewModel: FinishPaymentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FinishPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = loadUser()!!
        val transaction = loadTransaction()!!


        binding.reOrderBtnMain.setOnClickListener {
            shoppingCarFragment = ShoppingCarFragment.newInstance()
            menuActivity = MenuActivity()
            adapter.deleteProducts()
            deleteShoppingCar()

            val switchActivityIntent = Intent(this, MenuActivity::class.java)
            switchActivityIntent.putExtra("menuLicoresFragment", "menuLicoresFragment")
            startActivity(switchActivityIntent)
        }

        binding.showPUOrderProducts.setOnClickListener {
            OrderDialog().show(supportFragmentManager, "dialog")
        }

        binding.confirmPaymentBtnMain.setOnClickListener {
            adapter.deleteProducts()
            deleteShoppingCar()

            val switchActivityIntent = Intent(this, MenuActivity::class.java)
            switchActivityIntent.putExtra("principalFragment", "principalFragment")
            startActivity(switchActivityIntent)
        }

        binding.orderTotalAmountResultTV.text = formatPrice(calculatePaymentValue())

        val format = SimpleDateFormat("EEEE dd/MMM/yyyy hh:mm")
        val date = format.format(transaction.date!!)
        binding.orderDateResultTV.text = date

        binding.tVPaymentTypeMain.text = transaction.method


        GlobalScope.launch {
            Log.e(">>>","Antes de sumar ${user.diamonds}")
            val newDiamonds = (calculatePaymentValue() * 0.0001)
            val num = user.diamonds + newDiamonds
            user.diamonds = num
            Log.e(">>>","Después de sumar ${user.diamonds}")
            viewModel.updateUser(user, user.diamonds)
        }

        viewModel.updatedUser.observe(this) {
            saveUserSp(it)
            Log.e(">>>","$it")
        }
    }

    private fun calculatePaymentValue(): Int {
        val car: ArrayList<ProductsShoppingCarModel> = loadShoppingCar()!!
        var value = 0
        for (product in car) {
            value += product.price * product.lot
        }
        return value
    }

    private fun formatPrice(price: Int): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        return format.format(price)
    }

    private fun createTransaction(): TransactionModel {
        val user = loadUser()
        val shoppingCar = loadShoppingCar()
        val disco = loadDisco()
        val method = loadMethod()
        val code = CodeGenerator().generateCode()
        val date = Calendar.getInstance().time
        return TransactionModel(
            UUID.randomUUID().toString(),
            code,
            date,
            0,
            disco!!.id,
            method!!,
            shoppingCar,
            0,
            user!!.id,
            disco!!.name
        )
    }
    private fun loadTransaction(): TransactionModel? {
        val sp = getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("Transaction", "NO_USER")
        return if (json == "NO_USER") {
            null
        } else {
            Gson().fromJson(json, TransactionModel::class.java)
        }
    }

    private fun deleteShoppingCar() {
        val sp = getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        sp?.edit()?.putString("shoppingCar", null)?.apply()
    }

    private fun loadShoppingCar(): ArrayList<ProductsShoppingCarModel>? {
        val sp = getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("shoppingCar", "NO_CAR")
        return if (json == "NO_CAR") {
            null
        } else {
            val deserialized = object : TypeToken<ArrayList<ProductsShoppingCarModel>>() {}.type
            Gson().fromJson(json, deserialized)
        }
    }

    private fun loadDisco(): DiscoModel? {
        val sp = getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("Disco", "NO_DISCO")
        return if (json == "NO_DISCO") {
            null
        } else {
            Gson().fromJson(json, DiscoModel::class.java)
        }
    }

    private fun loadUser(): UserModel? {
        val sp = getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("Usuario", "NO_USER")
        return if (json == "NO_USER") {
            null
        } else {
            Gson().fromJson(json, UserModel::class.java)
        }
    }

    private fun loadMethod(): String? {
        val sp = getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("method", "NO_METHOD")
        return if (json == "NO_METHOD") {
            null
        } else {
            Gson().fromJson(json, String::class.java)
        }
    }

    private fun saveUserSp(user: UserModel) {
        val sp = getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp?.edit()?.putString("Usuario", json)?.apply()
    }
}