package com.riseup.riseup_users.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.DiamondHistModel
import com.riseup.riseup_users.view.DiamondHistView
import java.text.FieldPosition
import java.util.*
import kotlin.collections.ArrayList

class DiamondHistAdapter : RecyclerView.Adapter<DiamondHistView>(){

    private val diamondsTransactions = ArrayList<DiamondHistModel>()

    init {
        diamondsTransactions.add(DiamondHistModel("Espacio 10-60","52583270","8-10-2022",50))
        diamondsTransactions.add(DiamondHistModel("Espacio 10-60","52343530","1-10-2022",30))
        diamondsTransactions.add(DiamondHistModel("La Over","45188520","24-9-2022",23))
        diamondsTransactions.add(DiamondHistModel("Cardinals","45187541","17-9-2022",32))
        diamondsTransactions.add(DiamondHistModel("La Social","45185204","10-9-2022",20))
        diamondsTransactions.add(DiamondHistModel("La Premiere","45175102","3-9-2022",15))
        diamondsTransactions.add(DiamondHistModel("Living","45172015","27-8-2022",40))
    }


    //Genera un esqueleto gracias al XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): DiamondHistView {

        //XML a View
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.diamonds_trans_row, parent, false)
        val diamondsView = DiamondHistView(row)
        return diamondsView

    }

    //Con el esqueleto ya formado, se le ponen los datos correspondientes al esqueleto
    override fun onBindViewHolder(skeleton: DiamondHistView, position: Int) {
        val diamHist = diamondsTransactions[position]
        skeleton.discoName.text = diamHist.discoName
        skeleton.dateInfo.text = diamHist.date
        skeleton.idInfo.text = diamHist.transID
        skeleton.diamondsQty.text = diamHist.diamondsQty.toString()

    }

    //Este método permite al adaptador saber cuantos elementos se tienen
    override fun getItemCount(): Int {
        return diamondsTransactions.size
    }

}