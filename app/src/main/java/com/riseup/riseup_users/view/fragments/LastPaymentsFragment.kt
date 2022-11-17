package com.riseup.riseup_users.view.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.FragmentLastPaymentsBinding
import com.riseup.riseup_users.util.UserCardsPaymentAdapter

class LastPaymentsFragment : Fragment() {

    private var _binding: FragmentLastPaymentsBinding? = null
    private val binding get() = _binding!!

    //ESTADO
    private val adapter = UserCardsPaymentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLastPaymentsBinding.inflate(inflater, container, false)
        val view = binding.root

        //Recrear el estado
        val cardsRecycler = binding.lastPaymentsUserRecycleView
        cardsRecycler.setHasFixedSize(true)
        cardsRecycler.layoutManager = LinearLayoutManager(activity)
        cardsRecycler.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    companion object {
        @JvmStatic
        fun newInstance() = LastPaymentsFragment()
    }
}