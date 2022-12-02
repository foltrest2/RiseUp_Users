package com.riseup.riseup_users.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.ProductModel
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.view.FinalOrderListView

class FinalOrderListAdapter : RecyclerView.Adapter<FinalOrderListView>(){

    private val productList = ArrayList<ProductsShoppingCarModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinalOrderListView {
        //inflate: XML->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.final_order_list_row, parent, false)
        return FinalOrderListView(view)
    }

    override fun onBindViewHolder(holder: FinalOrderListView, position: Int) {
        val product = productList[position]
        holder.render(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun addProducts(products: List<ProductsShoppingCarModel>?){
        productList.addAll(products!!)
        notifyItemRangeInserted(0, productList.size)
    }

}