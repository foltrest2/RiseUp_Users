package com.riseup.riseup_users.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R

class DiamondPaymentView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var diamondPrice : TextView = itemView.findViewById(R.id.diamond_price)
    var descriptionProduct : TextView = itemView.findViewById(R.id.description_product)
    var productImage : ImageView = itemView.findViewById(R.id.product_image)

}