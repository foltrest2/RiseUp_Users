package com.riseup.riseup_users.view

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.DiscoCardRowBinding
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.ProductModel


class DiscoCardView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = DiscoCardRowBinding.bind(itemView)
    var imageUrl : String? = null

//    var discoName : TextView = itemView.findViewById(R.id.title_disco_card)
//    var visits : TextView = itemView.findViewById(R.id.visits_disco_card)
//    var discoImage : ImageView = itemView.findViewById(R.id.background_disco_card)
//    var discoCard : CardView = itemView.findViewById(R.id.disco_card)

    fun render(disco: DiscoModel, url : String, onClickListener:(DiscoModel) -> Unit){
        binding.titleDiscoCard.text = disco.name
        binding.discoCard.setOnClickListener { onClickListener(disco) }
        imageUrl = url
    }



}