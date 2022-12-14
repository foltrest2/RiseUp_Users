package com.riseup.riseup_users.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.DiscoCardRowBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.ProductModel
import kotlinx.coroutines.delay


class DiscoCardView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = DiscoCardRowBinding.bind(itemView)
    val title = binding.titleDiscoCard

//    var discoName : TextView = itemView.findViewById(R.id.title_disco_card)
//    var visits : TextView = itemView.findViewById(R.id.visits_disco_card)
//    var discoImage : ImageView = itemView.findViewById(R.id.background_disco_card)
//    var discoCard : CardView = itemView.findViewById(R.id.disco_card)

    fun render(disco: DiscoModel, onClickListener:(DiscoModel) -> Unit){

        binding.discoCard.setOnClickListener { onClickListener(disco) }

        Glide.with(binding.backgroundDiscoCard).load(disco.bannerCardURL).into(binding.backgroundDiscoCard)
    }



}