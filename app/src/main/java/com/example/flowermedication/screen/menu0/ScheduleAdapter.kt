package com.example.flowermedication.screen.menu0

import android.view.LayoutInflater
import android.view.View
import android.text.TextPaint
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import com.example.flowermedication.Schedule

class ScheduleAdapter : RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<Schedule>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_list_form, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val schedule = listData.get(position)
        holder.setSchedule(schedule)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun setSchedule(schedule: Schedule){
        val content = itemView.findViewById<TextView>(R.id.content_text)
        val time = itemView.findViewById<TextView>(R.id.time_text)

        content.text = schedule.content
        if(schedule.done) {
            time.text = "${schedule.hour} : ${schedule.minute}"
            content.paintFlags = content.paintFlags or TextPaint.STRIKE_THRU_TEXT_FLAG
        }
    }
}