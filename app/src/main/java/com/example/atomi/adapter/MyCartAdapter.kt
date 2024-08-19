package com.example.atomi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.atomi.R
import com.example.atomi.models.MyCartModel

class MyCartAdapter(private val context: Context, private val list: List<MyCartModel>) :
    RecyclerView.Adapter<MyCartAdapter.ViewHolder>() {

    private var totalAmount: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_cart_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.date.text = item.currentDate
        holder.time.text = item.currentTime
        holder.price.text = "${item.productPrice}đ"
        holder.name.text = item.productName
        holder.totalPrice.text = item.totalPrice.toString()
        holder.totalQuantity.text = item.totalQuantity

        totalAmount += item.totalPrice ?: 0
        val intent = Intent("MyTotalAmount").apply {
            putExtra("totalAmount", totalAmount)
        }

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.product_name)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val date: TextView = itemView.findViewById(R.id.current_date)
        val time: TextView = itemView.findViewById(R.id.current_time)
        val totalQuantity: TextView = itemView.findViewById(R.id.total_quantity)
        val totalPrice: TextView = itemView.findViewById(R.id.total_price)
    }
}
