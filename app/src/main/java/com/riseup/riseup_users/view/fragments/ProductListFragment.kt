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
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentProductListBinding
import com.riseup.riseup_users.model.ProductModel
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.ProductsListAdapter
import kotlin.collections.ArrayList

class ProductListFragment : Fragment() {


    private var _binding: FragmentProductListBinding?= null
    private val binding get() = _binding!!
    private lateinit var user: UserModel
    private var shoppingCar : ArrayList<ProductsShoppingCarModel> = arrayListOf()

    private lateinit var discoHomeFragment: DiscoHomeFragment

    //STATE
    private val adapter = ProductsListAdapter { thisProduct -> onClickListener(thisProduct) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root

        val temp = loadShoppingCar()
        if (temp != null){
            shoppingCar = temp!!
        }

        //Recrear el estado
        val productsListRecycler = binding.recyclerViewProductListSC
        productsListRecycler.setHasFixedSize(true)
        productsListRecycler.layoutManager = LinearLayoutManager(activity)
        productsListRecycler.adapter = adapter

        binding.atrasBtnProductsLMain.setOnClickListener {
            discoHomeFragment = DiscoHomeFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, discoHomeFragment)
            transaction.commit()
        }

        return view
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

    private fun saveUserSp(user: UserModel) {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp?.edit()?.putString("Usuario", json)?.apply()
    }

    private fun onClickListener(thisProduct: ProductModel) {
        val product = ProductsShoppingCarModel(thisProduct.image, thisProduct.name, thisProduct.price, thisProduct.quantity.inc())
        if (shoppingCar.contains(product)){
            var x = shoppingCar[shoppingCar.indexOf(product)].lot.inc()
            Toast.makeText(requireContext(), "Tienes $x de ${product.name}", Toast.LENGTH_LONG).show()
        }else{
            shoppingCar.add(product)
        }

        saveShoppingCar()
    }

    private fun saveShoppingCar(){
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(shoppingCar)
        sp?.edit()?.putString("shoppingCar", json)?.apply()
    }

    private fun loadShoppingCar(): ArrayList<ProductsShoppingCarModel>? {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("shoppingCar", "NO_CAR")
        return if (json == "NO_USER") {
            null
        } else {
            Gson().fromJson(json, ArrayList<ProductsShoppingCarModel>()::class.java)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductListFragment()
    }
}