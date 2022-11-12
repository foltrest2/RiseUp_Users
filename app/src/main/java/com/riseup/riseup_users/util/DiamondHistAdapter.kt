package com.riseup.riseup_users.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_users.R
import com.riseup.riseup_users.model.TransactionModel
import com.riseup.riseup_users.view.DiamondHistView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DiamondHistAdapter : RecyclerView.Adapter<DiamondHistView>(){

    private val diamondsTransactions = ArrayList<TransactionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): DiamondHistView {

        //XML a View
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.diamonds_trans_row, parent, false)
        val diamondsView = DiamondHistView(row)
        return diamondsView

    }

    //Con el esqueleto ya formado, se le ponen los datos correspondientes al esqueleto
    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(skeleton: DiamondHistView, position: Int) {
        val diamHist = diamondsTransactions[position]
        skeleton.discoName.text = diamHist.discoName

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.format(diamHist.date!!).toString()
        skeleton.dateInfo.setText(date)

        skeleton.idInfo.text = diamHist.id
        skeleton.diamondsQty.text = diamHist.diamonds.toString()

    }

    //Este método permite al adaptador saber cuantos elementos se tienen
    override fun getItemCount(): Int {
        return diamondsTransactions.size
    }

    fun addHistory(transaction : TransactionModel){
        diamondsTransactions.add(transaction)
        notifyItemInserted(diamondsTransactions.lastIndex)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear(){
        diamondsTransactions.clear()
        notifyDataSetChanged()
    }

}