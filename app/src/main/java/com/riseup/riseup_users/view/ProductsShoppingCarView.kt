package com.riseup.riseup_users.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R

class ProductsShoppingCarView(itemView: View) : RecyclerView.ViewHolder(itemView) {


    var productImage: ImageView = itemView.findViewById(R.id.productSCImg)
    var productName: TextView = itemView.findViewById(R.id.productSCnameTV)
    var productPrice: TextView = itemView.findViewById(R.id.productSCPriceTV)
    var productQuantity: TextView = itemView.findViewById(R.id.productSCQuantityTV)

    /*
     fun modifyCard(){

          var addBtn: ImageView = itemView.findViewById(R.id.addProductsSHIV)
          var deleteBtn:ImageView = itemView.findViewById(R.id.deleteProductsSHIV)
                    addBtn.visibility = View.GONE
                    deleteBtn.visibility = View.GONE
                    productQuantity.visibility = View.GONE

     }
     */

    init{


    }

}