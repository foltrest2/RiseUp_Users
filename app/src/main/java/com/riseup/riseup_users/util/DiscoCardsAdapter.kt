package com.riseup.riseup_users.util

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.DiscoCardModel
import com.riseup.riseup_users.model.DiscoModel
import com.riseup.riseup_users.model.ProductModel
import com.riseup.riseup_users.view.DiscoCardView
import com.riseup.riseup_users.view.MenuActivity


class DiscoCardsAdapter(private val onClickListener:(DiscoModel) -> Unit) : RecyclerView.Adapter<DiscoCardView>() {

    private val discoCards = ArrayList<DiscoModel>()

    //Genera un esqueleto gracias al XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoCardView {
        //XML a View
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.disco_card_row, parent, false)
        return DiscoCardView(row)
    }

    //Con el esqueleto ya formado, se le ponen los datos correspondientes al esqueleto
    override fun onBindViewHolder(skeleton: DiscoCardView, position: Int) {
        val discoCard = discoCards[position]
        skeleton.render(discoCard, onClickListener)
    }

    //Este método permite al adaptador saber cuantos elementos se tienen
    override fun getItemCount(): Int {
        return discoCards.size
    }

    fun removeDiscoCard(order: DiscoModel){
        val index = discoCards.indexOf(order)
        discoCards.remove(order)
        notifyItemRemoved(index)
    }

    fun addDiscoCard(order : DiscoModel){
        discoCards.add(order)
        notifyItemInserted(discoCards.lastIndex)
    }

    fun addAllDiscos(orders : ArrayList<DiscoModel>){
        discoCards.addAll(orders)
        notifyDataSetChanged()
    }

    fun reset(){
        discoCards.clear()
        notifyDataSetChanged()
    }

}
