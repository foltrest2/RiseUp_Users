package com.riseup.riseup_users

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.FragmentDiamondsPaymentBinding
import com.riseup.riseup_users.util.DiamondPaymentAdapter
import com.riseup.riseup_users.view.FinishPaymentActivity

class DiamondsPaymentFragment : Fragment() {

    private var _binding: FragmentDiamondsPaymentBinding? = null
    private val binding get() = _binding!!

    private lateinit var paymentSelectionFragment: PaymentSelectionFragment

    //STATE
    private val adapter = DiamondPaymentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDiamondsPaymentBinding.inflate(inflater,container,false)
        val view = binding.root

        //Recrear el estado
        val productsListRecycler = binding.diamondPaymentRecycler
        productsListRecycler.setHasFixedSize(true)
        productsListRecycler.layoutManager = LinearLayoutManager(activity)
        productsListRecycler.adapter = adapter

        binding.diamondPaymentBtn.setOnClickListener {
            val switchActivityIntent = Intent(requireContext(), FinishPaymentActivity::class.java)
            startActivity(switchActivityIntent)
        }

        binding.backArrowDiamondPayment.setOnClickListener {
            paymentSelectionFragment = PaymentSelectionFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentSelectionFragment)
            transaction.commit()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = DiamondsPaymentFragment()
    }
}