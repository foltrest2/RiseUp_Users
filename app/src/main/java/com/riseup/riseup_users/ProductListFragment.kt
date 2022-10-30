package com.riseup.riseup_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.FragmentProductListBinding
import com.riseup.riseup_users.util.ProductsListAdapter

class ProductListFragment : Fragment() {


    private var _binding: FragmentProductListBinding?= null
    private val binding get() = _binding!!

    private lateinit var discoHomeFragment: DiscoHomeFragment

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
            discoHomeFragment = DiscoHomeFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, discoHomeFragment)
            transaction.commit()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductListFragment()
    }
}