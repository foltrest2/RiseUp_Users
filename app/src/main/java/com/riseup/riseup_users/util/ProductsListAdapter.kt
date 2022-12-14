package com.riseup.riseup_users.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.ProductModel
import com.riseup.riseup_users.view.ProductsListView


class ProductsListAdapter(private val onClickListener:(ProductModel) -> Unit): RecyclerView.Adapter<ProductsListView>() {

    private val productList = ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListView {
        //inflate: XML->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.products_list_row, parent, false)
        return ProductsListView(view)
    }

    override fun onBindViewHolder(holder: ProductsListView, position: Int) {
        val product = productList[position]
        holder.render(product, onClickListener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun addAllProducts(products: List<ProductModel>?){
        productList.addAll(products!!)
        notifyItemRangeInserted(0, productList.size)
    }

}