package com.riseup.riseup_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riseup.riseup_users.databinding.FragmentPaymentSelectionBinding


class PaymentSelectionFragment : Fragment() {
    private var _binding: FragmentPaymentSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentSelectionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = PaymentSelectionFragment()
    }
}