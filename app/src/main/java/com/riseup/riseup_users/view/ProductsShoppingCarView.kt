package com.riseup.riseup_users.view

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.ProductsShoppingcarrowBinding
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.util.ProductsShoppingCarAdapter
import java.text.NumberFormat
import java.util.*

class ProductsShoppingCarView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ProductsShoppingcarrowBinding.bind(itemView)

    var delete: ImageView = itemView.findViewById(R.id.deleteProductsSHIV)
    var add: ImageView = itemView.findViewById(R.id.addProductsSHIV)
    var productQuantity: TextView = itemView.findViewById(R.id.productSCQuantityTV)
    var container : LinearLayout = itemView.findViewById(R.id.SCButtonsLY)

    fun render(product: ProductsShoppingCarModel){
        binding.productSCnameTV.text = product.name
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        binding.productSCPriceTV.text = format.format(product.price)
        binding.productSCQuantityTV.text = product.lot.toString()
        Glide.with(binding.productSCImg).load(product.imgURL).into(binding.productSCImg)
    }

}
