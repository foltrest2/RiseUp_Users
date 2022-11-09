package com.riseup.riseup_users.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riseup.riseup_users.databinding.FragmentUserCreditCardsPaymentsBinding
import com.riseup.riseup_users.view.ConfigUserPaymentsAddCardActivity

class UserCreditCardsPaymentsFragment : Fragment(){

    private var _binding: FragmentUserCreditCardsPaymentsBinding? = null
    private val binding get() = _binding!!

    //_binding -> Nulleable
    //binding -> Non-Nuneable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserCreditCardsPaymentsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.createCardUserPayBtn.setOnClickListener {
            startActivity(Intent(view.context, ConfigUserPaymentsAddCardActivity::class.java))
            onDestroyView()
        }

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


    companion object {
        @JvmStatic
        fun newInstance() = UserCreditCardsPaymentsFragment()

    }
}