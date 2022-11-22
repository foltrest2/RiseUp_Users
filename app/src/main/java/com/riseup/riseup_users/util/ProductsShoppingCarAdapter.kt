package com.riseup.riseup_users.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_users.R
import com.riseup.riseup_users.databinding.ProductsShoppingcarrowBinding
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.view.ProductsShoppingCarView
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class ProductsShoppingCarAdapter : RecyclerView.Adapter<ProductsShoppingCarView>() {

    private val productShCar = ArrayList<ProductsShoppingCarModel>()

    private lateinit var mListener : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick()
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsShoppingCarView {
        //inflate: XML->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.products_shoppingcarrow, parent, false)
        return ProductsShoppingCarView(view)
    }

    override fun onBindViewHolder(holder: ProductsShoppingCarView, position: Int) {
        val product = productShCar[position]
        holder.add.setOnClickListener {
            val car = loadShoppingCar(holder.add.context)
            var lot = car!![car.indexOf(product)].lot + 1
            car[car.indexOf(product)].lot = lot
            product.lot = lot
            holder.productQuantity.text = lot.toString()
            saveShoppingCar(holder.add.context, car)
            with(mListener) { onItemClick() }
        }
        holder.delete.setOnClickListener {
            val car = loadShoppingCar(holder.add.context)
            var lot = car!![car.indexOf(product)].lot - 1
            if (lot == 0){
                car.remove(product)
                productShCar.remove(product)
                notifyItemRemoved(position)
            }else {
                car[car.indexOf(product)].lot = lot
                product.lot = lot
                holder.productQuantity.text = lot.toString()
            }
            saveShoppingCar(holder.add.context, car)
            with(mListener) { onItemClick() }
        }
        holder.render(product)
    }

    override fun getItemCount(): Int {
        return productShCar.size
    }

    private fun loadShoppingCar(context: Context): java.util.ArrayList<ProductsShoppingCarModel>? {
        val sp = context.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("shoppingCar", "NO_CAR")
        return if (json == "NO_CAR") {
            null
        } else {
            val deserialized = object : TypeToken<java.util.ArrayList<ProductsShoppingCarModel>>() {}.type
            Gson().fromJson(json, deserialized)
        }
    }

    private fun saveShoppingCar(context: Context, car: java.util.ArrayList<ProductsShoppingCarModel>) {
        val sp = context.getSharedPreferences("RiseUpUser", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(car)
        sp?.edit()?.putString("shoppingCar", json)?.apply()
    }


    fun addProducts(shoppingCar : ArrayList<ProductsShoppingCarModel>) {
        productShCar.clear()
        productShCar.addAll(shoppingCar)
    }

    fun deleteProducts(){
        productShCar.clear()
    }

}
