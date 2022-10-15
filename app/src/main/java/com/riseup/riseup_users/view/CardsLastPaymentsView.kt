package com.riseup.riseup_users.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R

class CardsLastPaymentsView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //Identificar los UI componentes
    var discoName : TextView = itemView.findViewById(R.id.discoTitlePayedCardTV)
    var cardInfo : TextView = itemView.findViewById(R.id.cardReferenceNameTV)
    var amount : TextView = itemView.findViewById(R.id.amountPayedByCardTV)
    var payDate : TextView = itemView.findViewById(R.id.datePayeduserCardTV)
    var cardImg : ImageView = itemView.findViewById(R.id.cardLogoUserPaymentsImg)


}