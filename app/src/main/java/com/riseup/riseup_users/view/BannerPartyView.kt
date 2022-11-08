package com.riseup.riseup_users.view

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R

class BannerPartyView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var backgroundParty: ImageView = itemView.findViewById(R.id.background_party)
    var bannerCard: CardView = itemView.findViewById(R.id.banner_card)

}
