package com.example.flowermedication.screen.menu1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import java.util.*

class AdapterMonth: RecyclerView.Adapter<MonthView>() {
    val center = Int.MAX_VALUE / 2
    private var calendar = Calendar.getInstance()

    //inner class MonthView(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_month, parent, false)
        return MonthView(view)
    }

    override fun onBindViewHolder(holder: MonthView, position: Int) {
        holder.setMonth(calendar, center)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}

class MonthView(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun setMonth(calendar: Calendar, center : Int){
        val item_month_text : TextView = itemView.findViewById(R.id.item_month_text)
        val item_month_day_list : RecyclerView = itemView.findViewById(R.id.item_month_day_list)

        calendar.time = Date()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.MONTH, position - center)
        item_month_text.text = "${calendar.get(Calendar.YEAR)}년 ${calendar.get(Calendar.MONTH) + 1}월"
        val tempMonth = calendar.get(Calendar.MONTH)

        var dayList: MutableList<Date> = MutableList(6 * 7) { Date() }
        for(i in 0..5) {
            for(k in 0..6) {
                calendar.add(Calendar.DAY_OF_MONTH, (1-calendar.get(Calendar.DAY_OF_WEEK)) + k)
                dayList[i * 7 + k] = calendar.time
            }
            calendar.add(Calendar.WEEK_OF_MONTH, 1)
        }

        val dayListManager = GridLayoutManager(itemView.context, 7)
        val dayListAdapter = AdapterDay(tempMonth, dayList)

        item_month_day_list.apply {
            layoutManager = dayListManager
            adapter = dayListAdapter
        }

    }
}