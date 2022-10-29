package com.riseup.riseup_users.util

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.DiscoCardModel
import com.riseup.riseup_users.view.DiscoCardView
import com.riseup.riseup_users.view.DiscoHomeActivity


class DiscoCardsAdapter : RecyclerView.Adapter<DiscoCardView>() {

    private val discoCards = ArrayList<DiscoCardModel>()

    init {
        discoCards.add(DiscoCardModel("Espacio 10-60","52583270"))
        discoCards.add(DiscoCardModel("La Over","45188520"))
        discoCards.add(DiscoCardModel("Cardinals","45187541"))
        discoCards.add(DiscoCardModel("La Social","45185204"))
        discoCards.add(DiscoCardModel("La Premiere","45175102"))
        discoCards.add(DiscoCardModel("Living","45172015"))
    }


    //Genera un esqueleto gracias al XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoCardView {
        //XML a View
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.disco_card_row, parent, false)
        return DiscoCardView(row)
    }

    //Con el esqueleto ya formado, se le ponen los datos correspondientes al esqueleto
    override fun onBindViewHolder(skeleton: DiscoCardView, position: Int) {
        val discoCard = discoCards[position]
        skeleton.discoName.text = discoCard.name
        skeleton.visits.text = discoCard.visits
        skeleton.discoImage.setImageResource(R.mipmap.fondotarjeta1060)
        val context = skeleton.discoCard.context
        val intent = Intent(context, DiscoHomeActivity::class.java)
        skeleton.discoCard.setOnClickListener{
            startActivity(context,intent, Bundle())
        }
    }

    //Este método permite al adaptador saber cuantos elementos se tienen
    override fun getItemCount(): Int {
        return discoCards.size
    }

}