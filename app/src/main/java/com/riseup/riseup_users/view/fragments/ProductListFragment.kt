package com.riseup.riseup_users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentProductListBinding
import com.riseup.riseup_users.model.Product
import com.riseup.riseup_users.model.User
import com.riseup.riseup_users.util.ProductsListAdapter
import com.riseup.riseup_users.view.MenuActivity

class ProductListFragment : Fragment() {


    private var _binding: FragmentProductListBinding?= null
    private val binding get() = _binding!!
    private lateinit var user: User

    private lateinit var discoHomeFragment: DiscoHomeFragment

    //STATE
    private val adapter = ProductsListAdapter { thisProduct -> onClickListener(thisProduct) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root

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

    private fun loadUser(): User? {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("Usuario", "NO_USER")
        if (json == "NO_USER") {
            return null
        } else {
            return Gson().fromJson(json, User::class.java)
        }
    }

    private fun saveUserSp(user: User) {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp?.edit()?.putString("Usuario", json)?.apply()
    }

    private fun onClickListener(thisProduct: Product) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductListFragment()
    }
}