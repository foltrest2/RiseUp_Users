package com.riseup.riseup_users.view

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.BannerPartyRowBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.EventModel

class BannerPartyView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = BannerPartyRowBinding.bind(itemView)


    fun render(event : EventModel, onClickListener:(EventModel) -> Unit){
        binding.constraintBannerCard.setOnClickListener { onClickListener(event) }

        Glide.with(itemView).load(event.posterURL).into(binding.backgroundParty)
    }

}
