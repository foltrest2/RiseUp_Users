package com.riseup.riseup_users.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_users.databinding.FinalOrderListRowBinding
import com.riseup.riseup_users.databinding.ProductsListRowBinding
import com.riseup.riseup_users.model.ProductModel
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import java.text.NumberFormat
import java.util.*

class FinalOrderListView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = FinalOrderListRowBinding.bind(itemView)

//    var productImage: ImageView = itemView.findViewById(R.id.productListImg)
//    var productName: TextView = itemView.findViewById(R.id.productlistnameTV)
//    var productPrice: TextView = itemView.findViewById(R.id.productListPriceTV)
//    var productType: TextView = itemView.findViewById(R.id.productTypePLTV)
//    var addProductBtn: Button = itemView.findViewById(R.id.addProductPLBtn)

    fun render(product: ProductsShoppingCarModel){
        binding.productNameTVFOL.text = product.name
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        binding.productPriceTVFOL.text = format.format(product.price)
        binding.productQuantityTVFOL.text = product.lot.toString()
        Glide.with(itemView).load(product.imgURL).into(binding.productImgFOL)
    }

}