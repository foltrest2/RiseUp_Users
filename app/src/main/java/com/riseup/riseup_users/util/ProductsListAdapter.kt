package com.riseup.riseup_users.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.ProductsListModel
import com.riseup.riseup_users.view.ProductsListView


class ProductsListAdapter: RecyclerView.Adapter<ProductsListView>() {


    private val productList = ArrayList<ProductsListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListView {

        //inflate: XML->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.products_list_row, parent, false)
        return ProductsListView(view)
    }

    override fun onBindViewHolder(holder: ProductsListView, position: Int) {

        val producln = productList[position]
        holder.productName.text = producln.name
        holder.productType.text = producln.type
        holder.productPrice.text = producln.price.toBigDecimal().toString()
        holder.productImage.setImageResource(R.drawable.wineimg)
        holder.addProductBtn.setOnClickListener {
            
        }
    }

    override fun getItemCount(): Int {
        return productList.size

    }

    init{
       productList.add(ProductsListModel("wineimg","Vino azul",180000.0,"Tequila"))
        productList.add(ProductsListModel("wineimg","Vino azul",180000.0,"Tequila"))
        productList.add(ProductsListModel("wineimg","Whisky Jack Daniels",180000.0,"Whisky"))
        productList.add(ProductsListModel("wineimg","Whisky Jack Daniels",180000.0,"Whisky"))
        productList.add(ProductsListModel("wineimg","Whisky Jack Daniels",180000.0,"Whisky"))
        productList.add(ProductsListModel("wineimg","Aguardiente tapa azul",180000.0,"Aguardiente"))
        productList.add(ProductsListModel("wineimg","Aguardiente tapa azul",180000.0,"Aguardiente"))

    }


}