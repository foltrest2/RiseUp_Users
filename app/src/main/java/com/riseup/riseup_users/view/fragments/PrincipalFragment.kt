package com.riseup.riseup_users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentPrincipalBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.util.DiscoCardsAdapter
import com.riseup.riseup_users.viewmodel.MenuViewModel

class PrincipalFragment : Fragment() {

    private var _binding:FragmentPrincipalBinding? = null
    private val binding get() = _binding!!
    private lateinit var discoHomeFragment: DiscoHomeFragment
    private val viewModel : MenuViewModel by activityViewModels()
    //STATE
    private val adapter = DiscoCardsAdapter{discoModel -> onClickListener(discoModel)}

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

        viewModel.discos.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                adapter.reset()
                for(discos in it){
                    adapter.addDiscoCard(discos)
                }
            }
        }

        return view
    }

    private fun onClickListener(discoModel: DiscoModel) {
        discoHomeFragment = DiscoHomeFragment.newInstance()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, discoHomeFragment)
        transaction.commit()
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