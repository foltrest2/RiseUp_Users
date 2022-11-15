package com.riseup.riseup_users.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentPrincipalBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.DiscoCardsAdapter
import com.riseup.riseup_users.viewmodel.MenuViewModel

class PrincipalFragment : Fragment() {

    private var _binding:FragmentPrincipalBinding? = null
    private val binding get() = _binding!!
    private lateinit var discoHomeFragment: DiscoHomeFragment
    private val viewModel : MenuViewModel by activityViewModels()
    //STATE
    private val adapter = DiscoCardsAdapter{thisDisco -> onClickListener(thisDisco)}

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

        viewModel.discos.observe(viewLifecycleOwner){ discos ->
            adapter.reset()
            if(discos.isNotEmpty()){
                for(disco in discos){
                    adapter.addDiscoCard(disco)
                }
            }
        }

        viewModel.inComingImg.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                for(URL in it){
                    Log.e(">>>", "$URL passed")
                    adapter.setImage(URL)
                    
//                    Glide.with().load(url)
//                        .centerCrop()
//                        .into(binding.backgroundDiscoCard)
                }
                adapter.restart()
            }
        }

        return view
    }

    private fun saveDiscoSp(discoModel: DiscoModel) {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(discoModel)
        sp?.edit()?.putString("Disco", json)?.apply()
    }

    private fun onClickListener(discoModel: DiscoModel) {
        discoHomeFragment = DiscoHomeFragment.newInstance()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, discoHomeFragment)
        transaction.commit()
        saveDiscoSp(discoModel)
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