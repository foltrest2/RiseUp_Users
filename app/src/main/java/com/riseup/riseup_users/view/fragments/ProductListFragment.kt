package com.riseup.riseup_users.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentProductListBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.ProductModel
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.ProductsListAdapter
import com.riseup.riseup_users.viewmodel.MenuViewModel
import kotlin.collections.ArrayList

class ProductListFragment : Fragment() {


    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private lateinit var user: UserModel
    private var shoppingCar: ArrayList<ProductsShoppingCarModel> = arrayListOf()
    private val viewModel: MenuViewModel by activityViewModels()

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

        val disco = loadDisco()
        val temp = loadShoppingCar()
        if (temp != null) shoppingCar = temp
        if (disco != null){
            if (viewModel.getProducts(disco) != null) {
                adapter.addAllProducts(viewModel.getProducts(disco))
            }
        }

        var user = loadUser()


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

        Log.e(">>>", "$user")
        binding.youHaveDiamondsTxt.text = "¡Tienes ${user?.diamonds?.toInt()} diamantes!"

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

    private fun onClickListener(thisProduct: ProductModel) {
        if (loadShoppingCar() != null) {
            var car = loadShoppingCar()!!
            addToCar(thisProduct, car)
            saveShoppingCar(car)
        } else {
            addToCar(thisProduct, shoppingCar)
            saveShoppingCar(shoppingCar)
        }
        Toast.makeText(binding.youHaveDiamondsTxt.context, "Producto agregado", Toast.LENGTH_SHORT).show()
    }

    private fun addToCar(thisProduct: ProductModel, shoppingCar: ArrayList<ProductsShoppingCarModel>) {
        if (shoppingCar.isEmpty()) {
            shoppingCar.add(
                ProductsShoppingCarModel(
                    thisProduct.image,
                    thisProduct.name,
                    thisProduct.price,
                    1,
                    thisProduct.imageURL
                )
            )
            return
        }
        val temporal: ArrayList<ProductsShoppingCarModel> = arrayListOf()
        var found: ProductsShoppingCarModel? = null
        var index : Int = 0
        for (i in shoppingCar.indices) {
            if (shoppingCar[i].name == thisProduct.name) {
                found = shoppingCar[i]
                index = i
            }
        }

        if (found != null) {
            shoppingCar[index].lot += 1
        } else {
            temporal.add(
                ProductsShoppingCarModel(
                    thisProduct.image,
                    thisProduct.name,
                    thisProduct.price,
                    1,
                    thisProduct.imageURL
                )
            )
        }
        shoppingCar.addAll(temporal)
    }

    private fun saveShoppingCar(car: ArrayList<ProductsShoppingCarModel>) {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(car)
        sp?.edit()?.putString("shoppingCar", json)?.apply()
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

    companion object {
        @JvmStatic
        fun newInstance() = ProductListFragment()
    }
}