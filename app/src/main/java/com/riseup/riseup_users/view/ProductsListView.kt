package com.riseup.riseup_users.view

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.ProductsListRowBinding
import com.riseup.riseup_users.model.Product

class ProductsListView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ProductsListRowBinding.bind(itemView)

//    var productImage: ImageView = itemView.findViewById(R.id.productListImg)
//    var productName: TextView = itemView.findViewById(R.id.productlistnameTV)
//    var productPrice: TextView = itemView.findViewById(R.id.productListPriceTV)
//    var productType: TextView = itemView.findViewById(R.id.productTypePLTV)
//    var addProductBtn: Button = itemView.findViewById(R.id.addProductPLBtn)

    fun render(product: Product, onClickListener:(Product) -> Unit){
        binding.productTypePLTV.text = product.category
        binding.productListImg.setImageResource(R.drawable.wineimg)
        binding.productListPriceTV.text = product.price.toString()
        binding.productlistnameTV.text = product.name
        binding.addProductPLBtn.setOnClickListener { onClickListener(product) }
    }

}