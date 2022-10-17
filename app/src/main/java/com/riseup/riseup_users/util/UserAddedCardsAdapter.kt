package com.riseup.riseup_users.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.ProductsShoppingCarModel
import com.riseup.riseup_users.model.UserAddedCardsModel

import com.riseup.riseup_users.view.UserAddedCardsView

class UserAddedCardsAdapter:RecyclerView.Adapter<UserAddedCardsView>() {


    private val useraddedcards = ArrayList<UserAddedCardsModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAddedCardsView {
        //inflate: XML->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.creditcard_row,parent,false)
        val userAddedCardsViewHolder =UserAddedCardsView(view)
        return userAddedCardsViewHolder

    }

    override fun onBindViewHolder(holder: UserAddedCardsView, position: Int) {
        val ucards = useraddedcards[position]
        holder.cardHolder.text = ucards.holder
        holder.expireDate.text = ucards.date
        holder.cardNumber.text = ucards.cardNumber


    }

    override fun getItemCount(): Int {

        return useraddedcards.size
    }

    init{
         useraddedcards.add(UserAddedCardsModel("Kevin Andres","17/11/2022","4855 9255 2034 4211","2433"))
        useraddedcards.add(UserAddedCardsModel("Kevin Andres","17/11/2022","4855 9255 2034 4211","2433"))
        useraddedcards.add(UserAddedCardsModel("Kevin Andres","17/11/2022","4855 9255 2034 4211","2433"))
        useraddedcards.add(UserAddedCardsModel("Kevin Andres","17/11/2022","4855 9255 2034 4211","2433"))
        useraddedcards.add(UserAddedCardsModel("Kevin Andres","17/11/2022","4855 9255 2034 4211","2433"))
        useraddedcards.add(UserAddedCardsModel("Kevin Andres","17/11/2022","4855 9255 2034 4211","2433"))

    }

}