package com.example.flowermedication.screen.menu2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.DaySchedule
import com.example.flowermedication.R
import com.example.flowermedication.custom_view.TimeBarView
import com.example.flowermedication.get_data.getDaySchedule


class AdapterSchedule(private var position:Int) : RecyclerView.Adapter<ScheduleView>(){

    var schedules : MutableList<DaySchedule> = getDaySchedule(position)

    fun updateData(){
        schedules = getDaySchedule(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu2_schedule, parent, false)
        return ScheduleView(view)
    }

    override fun onBindViewHolder(holder: ScheduleView, position: Int) {
        holder.setSchedule(schedules, position)
    }

    override fun getItemCount(): Int {
        return schedules.size
    }
}

class ScheduleView(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun setSchedule(schedules : MutableList<DaySchedule>, position: Int){
        val schedule_name : TextView = itemView.findViewById(R.id.schedule_name)
        val time_bar : TimeBarView = itemView.findViewById(R.id.time_bar)
        val delete_btn : ImageButton = itemView.findViewById(R.id.delete_btn)

        schedule_name.text = schedules[position].schedule_name

        time_bar.setStartTime(schedules[position].start_hour, schedules[position].start_minute)
        time_bar.setEndTime(schedules[position].end_hour, schedules[position].end_minute)
        time_bar.invalidate()

        delete_btn.setOnClickListener{
            // 서버에 정보 보내서 삭제, 서버에서 데이터 제 전송 받는 등의 코드를 수행해서 다시 불러오기.
            // invalidate()
            Toast.makeText(itemView.context, "${schedules[position].schedule_name} 일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}