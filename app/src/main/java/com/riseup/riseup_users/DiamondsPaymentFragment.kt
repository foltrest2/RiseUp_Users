package com.riseup.riseup_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.FragmentDiamondsPaymentBinding
import com.riseup.riseup_users.databinding.FragmentPrincipalBinding
import com.riseup.riseup_users.util.DiamondPaymentAdapter
import com.riseup.riseup_users.util.DiscoCardsAdapter

class DiamondsPaymentFragment : Fragment() {

    private var _binding: FragmentDiamondsPaymentBinding? = null
    private val binding get() = _binding!!

    //STATE
    private val adapter = DiamondPaymentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDiamondsPaymentBinding.inflate(inflater,container,false)
        val view = binding.root

        //Recrear el estado
        val productsListRecycler = binding.diamondPaymentRecycler
        productsListRecycler.setHasFixedSize(true)
        productsListRecycler.layoutManager = LinearLayoutManager(activity)
        productsListRecycler.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = DiamondsPaymentFragment()
    }
}