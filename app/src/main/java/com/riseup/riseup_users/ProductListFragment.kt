package com.riseup.riseup_users

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.FragmentProductListBinding
import com.riseup.riseup_users.util.ProductsListAdapter
import com.riseup.riseup_users.view.DiscoHomeActivity
import com.riseup.riseup_users.view.MenuActivity


class ProductListFragment : Fragment() {


    private var _binding: FragmentProductListBinding?= null
    private val binding get() = _binding!!

    //STATE
    private val adapter = ProductsListAdapter()

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

            val switchActivityIntent = Intent(requireContext(), DiscoHomeActivity::class.java)
            startActivity(switchActivityIntent)


        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProductListFragment()
    }
}