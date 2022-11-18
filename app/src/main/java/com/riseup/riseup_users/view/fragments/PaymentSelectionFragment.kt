package com.riseup.riseup_users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.FragmentPaymentSelectionBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.model.TransactionModel
import com.riseup.riseup_users.model.UserModel
import com.riseup.riseup_users.util.CodeGenerator
import com.riseup.riseup_users.util.PaymentDialog
import com.riseup.riseup_users.util.ProductsShoppingCarAdapter
import com.riseup.riseup_users.viewmodel.MenuViewModel
import java.util.*


class PaymentSelectionFragment : Fragment() {
    private var _binding: FragmentPaymentSelectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var paymentCodeFragment: PaymentCodeFragment
    private lateinit var diamondsPaymentFragment: DiamondsPaymentFragment
    private lateinit var shoppingCarFragment: ShoppingCarFragment
    private val viewModel : MenuViewModel by activityViewModels()
    private val adapter = ProductsShoppingCarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentSelectionBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.atrasBtnCarrito.setOnClickListener {
            shoppingCarFragment = ShoppingCarFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, shoppingCarFragment)
            transaction.commit()
        }

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
            saveMethod("Tarjeta")
        }
        binding.pagarNequiBtn.setOnClickListener {
            paymentCodeFragment = PaymentCodeFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentCodeFragment)
            transaction.commit()
            saveMethod("Nequi")
        }

        binding.pagarDaviplataBtn.setOnClickListener {
            paymentCodeFragment = PaymentCodeFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentCodeFragment)
            transaction.commit()
            saveMethod("Daviplata")
        }

        binding.pagarConDiamantesBtn.setOnClickListener {
            diamondsPaymentFragment = DiamondsPaymentFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer,  diamondsPaymentFragment)
            transaction.commit()
            saveMethod("Diamantes")
        }

    }

    private fun saveMethod(method: String) {
        val sp = context?.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(method)
        sp?.edit()?.putString("method", json)?.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PaymentSelectionFragment()
    }
}