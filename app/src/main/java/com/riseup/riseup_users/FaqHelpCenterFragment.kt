package com.riseup.riseup_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riseup.riseup_users.databinding.FragmentFaqHelpCenterBinding


class FaqHelpCenterFragment : Fragment() {

    private var _binding: FragmentFaqHelpCenterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFaqHelpCenterBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    companion object {
        @JvmStatic
        fun newInstance() = FaqHelpCenterFragment()

    }
}