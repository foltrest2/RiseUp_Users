package com.riseup.riseup_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riseup.riseup_users.databinding.FragmentPaymentCodeBinding


class PaymentCodeFragment : Fragment() {

    private var _binding: FragmentPaymentCodeBinding? = null
    private val binding get() = _binding!!
    private lateinit var paymentSelectionFragment: PaymentSelectionFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentCodeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.atrasBtnOrderCode.setOnClickListener{
            paymentSelectionFragment = PaymentSelectionFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentSelectionFragment)
            transaction.commit()

        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = PaymentCodeFragment()
    }
}