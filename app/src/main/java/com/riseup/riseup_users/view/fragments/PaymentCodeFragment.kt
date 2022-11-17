package com.riseup.riseup_users.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentPaymentCodeBinding
import com.riseup.riseup_users.view.FinishPaymentActivity


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

        binding.orderCodeLYMain.setOnClickListener {

            val switchActivityIntent = Intent(requireContext(), FinishPaymentActivity::class.java)
            startActivity(switchActivityIntent)

        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = PaymentCodeFragment()
    }
}