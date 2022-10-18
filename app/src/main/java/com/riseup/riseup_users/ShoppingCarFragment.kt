package com.riseup.riseup_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.FragmentShoppingCarBinding
import com.riseup.riseup_users.util.ProductsShoppingCarAdapter
import kotlinx.android.synthetic.main.fragment_shopping_car.*


class ShoppingCarFragment : Fragment() {

    private var _binding: FragmentShoppingCarBinding?= null
    private val binding get() = _binding!!
    private lateinit var paymentSelectionFragment: PaymentSelectionFragment
    private lateinit var productListFragment: ProductListFragment
    //STATE
    private val adapter = ProductsShoppingCarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShoppingCarBinding.inflate(inflater, container, false)
        val view = binding.root

        //Recrear el estado
        val productsShoppingCarRecycler = binding.productsSCRV
        productsShoppingCarRecycler.setHasFixedSize(true)
        productsShoppingCarRecycler.layoutManager = LinearLayoutManager(activity)
        productsShoppingCarRecycler.adapter = adapter
        binding.payBtn.setOnClickListener {
            paymentSelectionFragment = PaymentSelectionFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, paymentSelectionFragment)
            transaction.commit()
        }

        binding.atrasBtnDiscotecaMain.setOnClickListener {

            productListFragment = ProductListFragment.newInstance()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, productListFragment)
            transaction.commit()


        }

        return view
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = ShoppingCarFragment()
    }
}