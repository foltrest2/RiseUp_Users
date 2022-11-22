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
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentDiscoHomeBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.EventModel
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.BannerPartyAdapter
import com.riseup.riseup_users.viewmodel.DiscoCardViewModel
import com.riseup.riseup_users.viewmodel.MenuViewModel

class DiscoHomeFragment : Fragment() {

    private var _binding: FragmentDiscoHomeBinding?= null
    private val binding get() = _binding!!
    private val viewModel : MenuViewModel by activityViewModels()

    private lateinit var productListFragment: ProductListFragment

    private val adapter = BannerPartyAdapter{thisEvent -> onClickListener(thisEvent)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDiscoHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        val disco = loadDisco()

        setDiscoPropieties(disco!!)


        binding.viewAlcoholMenuDiscoSelected.setOnClickListener {
            productListFragment = ProductListFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, productListFragment)
            transaction.commit()
        }
        var user : UserModel? = null
        if (loadUser() != null) user = loadUser()!!

        binding.diamondsInfoDiscoSelected.text = "¡Tienes ${user?.diamonds?.toInt()} diamantes!"

        val bannersRecycler = binding.bannersRecyclerView
        bannersRecycler.setHasFixedSize(true)
        bannersRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        bannersRecycler.adapter = adapter

        //adapter.reset()
        for (event in disco.eventsID){
            Log.e(">>>","Evento agregado: ${event}")
            adapter.addEventCard(event)
        }


        return view
    }

    private fun onClickListener(thisEvent: EventModel) {
        Toast.makeText(requireContext(),thisEvent.toString(),Toast.LENGTH_SHORT).show()

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

    private fun loadDisco(): DiscoModel? {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("Disco", "NO_DISCO")
        return if (json == "NO_DISCO") {
            null
        } else {
            Gson().fromJson(json, DiscoModel::class.java)
        }
    }

    private fun setDiscoPropieties(disco : DiscoModel){
        binding.titleDiscoSelected.text = disco.name
        Glide.with(this).load(disco.bannerBackgroundURL).into(binding.backgroundDiscoHome)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DiscoHomeFragment()
    }

}