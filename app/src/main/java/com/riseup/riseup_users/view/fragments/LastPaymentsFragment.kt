package com.riseup.riseup_users.view.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_users.databinding.FragmentLastPaymentsBinding
import com.riseup.riseup_users.util.UserCardsPaymentAdapter
import com.riseup.riseup_users.viewmodel.ConfigUserPaysViewModel

class LastPaymentsFragment : Fragment() {

    private var _binding: FragmentLastPaymentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ConfigUserPaysViewModel by activityViewModels()

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

        viewModel.payments.observe(viewLifecycleOwner){
            if(it.isNotEmpty()) {
                adapter.reset()
                for (pays in it) {
                    adapter.addPay(pays)
                }
            }
        }

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