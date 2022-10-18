package com.riseup.riseup_users.util


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.view.ProductsShoppingCarView


class ProductsShoppingCarAdapter: RecyclerView.Adapter<ProductsShoppingCarView>() {


    private val productShCar = ArrayList<ProductsShoppingCarModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsShoppingCarView {

        //inflate: XML->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.products_shoppingcarrow,parent,false)
        val productSHViewHolder = ProductsShoppingCarView(view)
        return productSHViewHolder
    }

    override fun onBindViewHolder(holder: ProductsShoppingCarView, position: Int) {
        val producn = productShCar[position]
        holder.productPrice.text = producn.price.toBigDecimal().toString()
        holder.productName.text = producn.name
        holder.productImage.setImageResource(R.drawable.wineimg)
        holder.productQuantity.text = producn.quantity.toString()



    }


    override fun getItemCount(): Int {
        return productShCar.size
        TODO("Not yet implemented")
    }

    init{
        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
    }
}