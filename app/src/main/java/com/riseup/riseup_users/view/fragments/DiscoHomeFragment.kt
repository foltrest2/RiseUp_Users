package com.riseup.riseup_users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentDiscoHomeBinding
import com.riseup.riseup_users.util.BannerPartyAdapter
import com.riseup.riseup_users.viewmodel.DiscoCardViewModel
import com.riseup.riseup_users.viewmodel.MenuViewModel

class DiscoHomeFragment : Fragment() {

    private var _binding: FragmentDiscoHomeBinding?= null
    private val binding get() = _binding!!
    private val viewModel : MenuViewModel by activityViewModels()

    private lateinit var productListFragment: ProductListFragment

    private val adapter = BannerPartyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDiscoHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.viewAlcoholMenuDiscoSelected.setOnClickListener {
            productListFragment = ProductListFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, productListFragment)
            transaction.commit()
        }

        val bannersRecycler = binding.bannersRecyclerView
        bannersRecycler.setHasFixedSize(true)
        bannersRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)
        bannersRecycler.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = DiscoHomeFragment()
    }

}