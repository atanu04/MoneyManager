package com.example.moneymanager

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.Tran
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerAdapter(private val listener: TranClicked) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var tranList  = emptyList<Tran>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val desc: TextView = itemView.row_desc_id
        val date: TextView = itemView.row_date_id
        val sign : TextView = itemView.row_plus_id
        val credit: TextView = itemView.row_credit_id
        val debit: TextView = itemView.row_debit_id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        val viewHolder = ViewHolder(view)
        view.setOnLongClickListener {
            listener.onItemLongClicked(tranList[tranList.size - viewHolder.adapterPosition - 1])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTran = tranList[tranList.size - position -1]
        holder.desc.text= currentTran.desc
        holder.date.text = currentTran.date
        if(currentTran.type == 'c'){
            holder.sign.text = "+"
            holder.debit.text =" "
            holder.credit.text = currentTran.amount.toString()
        }
        else{
            holder.sign.text= "-"
            holder.credit.text=" "
            holder.debit.text = currentTran.amount.toString()
        }
    }

    override fun getItemCount(): Int {
       return tranList.size
    }

    fun setData(trans:List<Tran>){
        this.tranList = trans
        notifyDataSetChanged()
    }

    fun totalSum():Int{
        var len = tranList.size-1
        var sum = 0
        while(len!=-1){
            if(tranList[len].type == 'c'){
                sum+=tranList[len].amount
            }
            else{
                sum-=tranList[len].amount
            }
            len -= 1
        }
        return sum
    }
}
interface TranClicked{
    fun onItemLongClicked(tran: Tran):Boolean
}

