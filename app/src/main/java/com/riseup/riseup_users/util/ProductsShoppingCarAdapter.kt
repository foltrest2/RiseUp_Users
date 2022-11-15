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
        val view = inflater.inflate(R.layout.products_shoppingcarrow, parent, false)
        return ProductsShoppingCarView(view)
    }

    override fun onBindViewHolder(holder: ProductsShoppingCarView, position: Int) {
        val producn = productShCar[position]
        holder.productPrice.text = producn.price.toBigDecimal().toString()
        holder.productName.text = producn.name
        holder.productImage.setImageResource(R.drawable.wineimg)
        holder.productQuantity.text = producn.lot.toString()
    }


    override fun getItemCount(): Int {
        return productShCar.size
    }

    fun addProducts(shoppingCar : ArrayList<ProductsShoppingCarModel>) {
        productShCar.clear()
        productShCar.addAll(shoppingCar)
    }

    fun deleteProducts(){
        productShCar.clear()
    }

//    init{
//        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
//        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
//        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
//        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
//        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
//        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
//        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
//        productShCar.add(ProductsShoppingCarModel("wineimg","Vino azul",180000.0,12))
//    }
}