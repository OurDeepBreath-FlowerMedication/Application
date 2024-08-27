package com.example.flowermedication.screen.menu1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import java.util.*

class AdapterDay(val tempMonth:Int, val dayList: MutableList<Date>): RecyclerView.Adapter<DayView>() {
    val ROW = 6

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_day, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        holder.setDay(position, tempMonth, dayList)
    }

    override fun getItemCount(): Int {
        return ROW * 7
    }
}

class DayView(val layout: View): RecyclerView.ViewHolder(layout){
    fun setDay(position : Int, tempMonth:Int, dayList: MutableList<Date>){
        val item_day_text : TextView = layout.findViewById(R.id.item_day_text)
        layout.setOnClickListener {
            Toast.makeText(layout.context, "${dayList[position]}", Toast.LENGTH_SHORT).show()
        }
        item_day_text.text = dayList[position].date.toString()

        item_day_text.setTextColor(when(position % 7) {
            0 -> Color.RED
            6 -> Color.BLUE
            else -> Color.BLACK
        })

        if(tempMonth != dayList[position].month) {
            item_day_text.alpha = 0.4f
        }
    }
}