package com.riseup.riseup_users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentShoppingCarBinding
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.util.ProductsShoppingCarAdapter
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class ShoppingCarFragment : Fragment(){

    private var _binding: FragmentShoppingCarBinding?= null
    private val binding get() = _binding!!
    private lateinit var paymentSelectionFragment: PaymentSelectionFragment
    private lateinit var productListFragment: ProductListFragment

    //STATE
    private val adapter = ProductsShoppingCarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShoppingCarBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter.setOnItemClickListener(object : ProductsShoppingCarAdapter.OnItemClickListener{
            override fun onItemClick() {
                binding.DCPriceTV.text = formatPrice(calculatePaymentValue())
            }
        })

        if (loadShoppingCar() != null) adapter.addProducts(loadShoppingCar()!!)

        //Recrear el estado
        val productsShoppingCarRecycler = binding.productsSCRV
        productsShoppingCarRecycler.setHasFixedSize(true)
        productsShoppingCarRecycler.layoutManager = LinearLayoutManager(activity)
        productsShoppingCarRecycler.adapter = adapter
        binding.payBtn.setOnClickListener {
            if (adapter.itemCount > 0) {
                paymentSelectionFragment = PaymentSelectionFragment.newInstance()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, paymentSelectionFragment)
                transaction.commit()
            }else{
                Toast.makeText(requireContext(), "¡No tienes productos para pagar!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.atrasBtnDiscotecaMain.setOnClickListener {
            productListFragment = ProductListFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, productListFragment)
            transaction.commit()
        }

        binding.DCPriceTV.text = formatPrice(calculatePaymentValue())

        return view
    }

    private fun calculatePaymentValue() : Int{
        var car : ArrayList<ProductsShoppingCarModel> = arrayListOf()
        var value = 0
        if (loadShoppingCar() != null) {
            car = loadShoppingCar()!!
            for (product in car){
                value += product.price * product.lot
            }
        }
        return value
    }

    private fun formatPrice(price : Int) : String{
        var format : NumberFormat = NumberFormat.getCurrencyInstance(Locale("en","US"))
        return format.format(price)
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

    companion object {
        @JvmStatic
        fun newInstance() = ShoppingCarFragment()
    }


}