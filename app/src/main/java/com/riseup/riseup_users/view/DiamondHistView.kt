package com.riseup.riseup_users.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R

class DiamondHistView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    //Identificar los UI componentes
    var discoName : TextView = itemView.findViewById(R.id.discoNameTV)
    var dateInfo : TextView = itemView.findViewById(R.id.dateInfoTV)
    var idInfo : TextView = itemView.findViewById(R.id.idInfoTV)
    var diamondsQty : TextView = itemView.findViewById(R.id.diamondsQtyTV)
    var dateTitle : TextView = itemView.findViewById(R.id.dateTitle)
    var idTitle : TextView = itemView.findViewById(R.id.idTitleTV)
    var diamImg : ImageView = itemView.findViewById(R.id.diamonImg)





}