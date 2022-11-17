package com.riseup.riseup_users.util

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.*
import com.riseup.riseup_users.view.DiscoCardView
import com.riseup.riseup_users.view.MenuActivity
import kotlinx.coroutines.delay


class DiscoCardsAdapter(private val onClickListener:(DiscoModel) -> Unit) : RecyclerView.Adapter<DiscoCardView>() {

    private val discoCards = ArrayList<DiscoModel>()
    //private var _url: String = ""



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
        skeleton.title.text = discoCard.name
        skeleton.render(discoCard, onClickListener)
    }

    //Este método permite al adaptador saber cuantos elementos se tienen
    override fun getItemCount(): Int {
        return discoCards.size
    }

    fun restart(){
        var copy:ArrayList<DiscoModel> = arrayListOf()
        copy.addAll(discoCards)
        discoCards.clear()
        discoCards.addAll(copy)
        notifyDataSetChanged()
    }

    fun setImage(url: String){

    }

    fun removeDiscoCard(discoCard: DiscoModel){
        val index = discoCards.indexOf(discoCard)
        discoCards.remove(discoCard)
        notifyItemRemoved(index)
    }


    fun addDiscoCard(discoCard : DiscoModel){
        discoCards.add(discoCard)
        notifyItemInserted(discoCards.lastIndex)
    }

    fun addAllDiscos(discos : ArrayList<DiscoModel>){
        discoCards.addAll(discos)
        notifyDataSetChanged()
    }

    fun reset(){
        discoCards.clear()
        notifyDataSetChanged()
    }



}
