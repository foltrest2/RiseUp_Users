package com.riseup.riseup_users.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentDiamondsPaymentBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.DiamondPaymentAdapter
import com.riseup.riseup_users.view.FinishPaymentActivity

class DiamondsPaymentFragment : Fragment() {

    private var _binding: FragmentDiamondsPaymentBinding? = null
    private val binding get() = _binding!!

    private lateinit var paymentSelectionFragment: PaymentSelectionFragment

    //STATE
    private val adapter = DiamondPaymentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDiamondsPaymentBinding.inflate(inflater,container,false)
        val view = binding.root

        //Recrear el estado
        val productsListRecycler = binding.diamondPaymentRecycler
        productsListRecycler.setHasFixedSize(true)
        productsListRecycler.layoutManager = LinearLayoutManager(activity)
        productsListRecycler.adapter = adapter

        val user = loadUser()

        binding.diamondPaymentBtn.setOnClickListener {
            val switchActivityIntent = Intent(requireContext(), FinishPaymentActivity::class.java)
            startActivity(switchActivityIntent)
        }

        binding.backArrowDiamondPayment.setOnClickListener {
            paymentSelectionFragment = PaymentSelectionFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentSelectionFragment)
            transaction.commit()
        }

        binding.diamondQuantity.text = "${user?.diamonds}"



        return view
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
        fun newInstance() = DiamondsPaymentFragment()
    }
}