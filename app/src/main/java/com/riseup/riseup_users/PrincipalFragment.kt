package com.riseup.riseup_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.FragmentPrincipalBinding
import com.riseup.riseup_users.util.DiscoCardsAdapter

class PrincipalFragment : Fragment() {

    private var _binding:FragmentPrincipalBinding? = null
    private val binding get() = _binding!!

    //STATE
    private val adapter = DiscoCardsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrincipalBinding.inflate(inflater,container,false)
        val view = binding.root

        //Recrear el estado
        val productsListRecycler = binding.recyclerPrincipalFragment
        productsListRecycler.setHasFixedSize(true)
        productsListRecycler.layoutManager = LinearLayoutManager(activity)
        productsListRecycler.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = PrincipalFragment()
    }
}