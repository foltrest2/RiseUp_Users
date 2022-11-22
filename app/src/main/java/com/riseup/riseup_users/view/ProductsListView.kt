package com.riseup.riseup_users.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.ProductsListRowBinding
import com.riseup.riseup_users.model.ProductModel
import java.text.NumberFormat
import java.util.*

class ProductsListView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ProductsListRowBinding.bind(itemView)

//    var productImage: ImageView = itemView.findViewById(R.id.productListImg)
//    var productName: TextView = itemView.findViewById(R.id.productlistnameTV)
//    var productPrice: TextView = itemView.findViewById(R.id.productListPriceTV)
//    var productType: TextView = itemView.findViewById(R.id.productTypePLTV)
//    var addProductBtn: Button = itemView.findViewById(R.id.addProductPLBtn)

    fun render(product: ProductModel, onClickListener:(ProductModel) -> Unit){
        binding.productTypePLTV.text = product.category
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        binding.productListPriceTV.text = format.format(product.price)
        //binding.productListImg.setImageResource(R.drawable.wineimg)
        binding.productlistnameTV.text = product.name
        binding.addProductPLBtn.setOnClickListener { onClickListener(product) }
        Glide.with(itemView).load(product.imageURL).into(binding.productListImg)

    }

}