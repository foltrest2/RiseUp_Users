package com.riseup.riseup_users.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R

class DiscoCardView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var discoName : TextView = itemView.findViewById(R.id.title_disco_card)
    var visits : TextView = itemView.findViewById(R.id.visits_disco_card)
    var discoImage : ImageView = itemView.findViewById(R.id.background_disco_card)

}