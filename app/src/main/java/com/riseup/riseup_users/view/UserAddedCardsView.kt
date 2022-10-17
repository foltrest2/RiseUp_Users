package com.riseup.riseup_users.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R

class UserAddedCardsView(itemView:View):RecyclerView.ViewHolder(itemView) {
    var cardNumber: TextView = itemView.findViewById(R.id.userCardNumberTVPU)
    var cardHolder: TextView = itemView.findViewById(R.id.userCardHolderTVPU)
    var expireDate: TextView = itemView.findViewById(R.id.userExpireDateTextPU)

    init{


    }

}