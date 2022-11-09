package com.riseup.riseup_users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentPaymentSelectionBinding
import com.riseup.riseup_users.util.PaymentDialog


class PaymentSelectionFragment : Fragment() {
    private var _binding: FragmentPaymentSelectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var paymentCodeFragment: PaymentCodeFragment
    private lateinit var diamondsPaymentFragment: DiamondsPaymentFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentSelectionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pagarTarjetaBtn.setOnClickListener {
           PaymentDialog(
                onSubmitClickListener =  {
                    Toast.makeText(requireContext(), "usted ingreso", Toast.LENGTH_SHORT).show()
                }
            ).show(parentFragmentManager,"dialog")

        }
        binding.pagarNequiBtn.setOnClickListener {

            paymentCodeFragment = PaymentCodeFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentCodeFragment)
            transaction.commit()

        }

        binding.pagarDaviplataBtn.setOnClickListener {

            paymentCodeFragment = PaymentCodeFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentCodeFragment)
            transaction.commit()
        }

        binding.pagarConDiamantesBtn.setOnClickListener {
            diamondsPaymentFragment = DiamondsPaymentFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer,  diamondsPaymentFragment)
            transaction.commit()

        }

    }

    companion object {

        // TODO: Rename and change types and number of parameter
        @JvmStatic
        fun newInstance() = PaymentSelectionFragment()
    }
}