package com.riseup.riseup_users.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentPaymentCodeBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.model.TransactionModel
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.CodeGenerator
import com.riseup.riseup_users.view.FinishPaymentActivity
import com.riseup.riseup_users.viewmodel.MenuViewModel
import java.util.*


class PaymentCodeFragment : Fragment() {

    private var _binding: FragmentPaymentCodeBinding? = null
    private val binding get() = _binding!!
    private lateinit var paymentSelectionFragment: PaymentSelectionFragment
    private val viewModel : MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentCodeBinding.inflate(inflater, container, false)
        val view = binding.root

        val code = CodeGenerator().generateCode()
        binding.orderCodeLYMain.text = code

        val user = loadUser()
        val transaction = createTransaction(code)
        viewModel.saveTransaction(transaction, user!!)

        binding.atrasBtnOrderCode.setOnClickListener{
            paymentSelectionFragment = PaymentSelectionFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentSelectionFragment)
            transaction.commit()
        }

        binding.orderCodeLYMain.setOnClickListener {
            val switchActivityIntent = Intent(requireContext(), FinishPaymentActivity::class.java)
            startActivity(switchActivityIntent)
        }

        return view
    }

    private fun createTransaction(code: String): TransactionModel {
        val user = loadUser()
        val shoppingCar = loadShoppingCar()
        val disco = loadDisco()
        val method = loadMethod()
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

    private fun loadShoppingCar(): ArrayList<ProductsShoppingCarModel>? {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("shoppingCar", "NO_CAR")
        return if (json == "NO_CAR") {
            null
        } else {
            val deserialized = object : TypeToken<ArrayList<ProductsShoppingCarModel>>() {}.type
            Gson().fromJson(json, deserialized)
        }
    }

    private fun loadDisco(): DiscoModel? {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("Disco", "NO_DISCO")
        return if (json == "NO_DISCO") {
            null
        } else {
            Gson().fromJson(json, DiscoModel::class.java)
        }
    }

    private fun loadUser(): UserModel? {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("Usuario", "NO_USER")
        return if (json == "NO_USER") {
            null
        } else {
            Gson().fromJson(json, UserModel::class.java)
        }
    }

    private fun loadMethod(): String? {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("method", "NO_METHOD")
        return if (json == "NO_METHOD") {
            null
        } else {
            Gson().fromJson(json, String::class.java)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PaymentCodeFragment()
    }
}