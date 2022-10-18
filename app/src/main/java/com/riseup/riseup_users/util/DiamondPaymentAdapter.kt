package com.riseup.riseup_users.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.DiamondPaymentModel
import com.riseup.riseup_users.view.DiamondPaymentView

class DiamondPaymentAdapter: RecyclerView.Adapter<DiamondPaymentView>() {

    private val productList = ArrayList<DiamondPaymentModel>()

    init{
        productList.add(DiamondPaymentModel(5,"Aguardiente tapa azul"))
        productList.add(DiamondPaymentModel(5,"Aguardiente tapa azul"))
        productList.add(DiamondPaymentModel(5,"Aguardiente tapa azul"))
        productList.add(DiamondPaymentModel(5,"Aguardiente tapa azul"))
        productList.add(DiamondPaymentModel(5,"Aguardiente tapa azul"))
        productList.add(DiamondPaymentModel(5,"Aguardiente tapa azul"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiamondPaymentView {
        //inflate: XML->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.diamond_payment_row, parent, false)
        return DiamondPaymentView(view)
    }

    override fun onBindViewHolder(holder: DiamondPaymentView, position: Int) {
        val products = productList[position]
        holder.diamondPrice.text = products.diamondPrice.toString()
        holder.descriptionProduct.text = products.descriptionProduct
        holder.productImage.setImageResource(R.drawable.wineimg)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}