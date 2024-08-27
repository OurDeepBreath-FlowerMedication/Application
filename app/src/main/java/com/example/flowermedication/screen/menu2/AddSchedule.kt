package com.example.flowermedication.screen.menu2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.flowermedication.R
import com.example.flowermedication.custom_view.CustomTimePicker
import com.example.flowermedication.custom_view.UnderLine
import com.example.flowermedication.get_data.selectiedSchedule

class AddSchedule : AppCompatActivity() {

    val schedule_list : List<String> = listOf("--일정 선택--", "직접 입력", "아침", "점심", "저녁")
    val selectied_schedule : List<Boolean> = selectiedSchedule()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu2_add_schedule)

        var schedule_spinner : Spinner = findViewById(R.id.schedule_spinner)
        schedule_spinner.adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule_list){
            override fun isEnabled(position: Int): Boolean {
                if (position == 0 || (position>1 && selectied_schedule[position-2])) {
                    return false
                } else { return true }
            }
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val textView = view as TextView
                if (position == 0 || (position>1 && selectied_schedule[position-2])) {
                    textView.setTextColor(Color.GRAY)
                } else {
                    textView.setTextColor(Color.BLACK)
                }
                return view
            }
        }

        schedule_spinner.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val text : TextView = findViewById(R.id.custom_schedule_text)
                val bar : UnderLine = findViewById(R.id.custom_schedule_bar)
                val edit : EditText = findViewById(R.id.custom_schedule_edit)
                if(position == 1){
                    text.visibility = View.VISIBLE
                    bar.visibility = View.VISIBLE
                    edit.visibility = View.VISIBLE
                }else{
                    text.visibility = View.GONE
                    bar.visibility = View.GONE
                    edit.visibility = View.GONE
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val start_time_set : CustomTimePicker = findViewById(R.id.start_time_set)
        val end_time_set : CustomTimePicker = findViewById(R.id.end_time_set)

        val day_cycles = listOf<View>(findViewById(R.id.day_cycle0), findViewById(R.id.day_cycle1),
            findViewById(R.id.day_cycle2), findViewById(R.id.day_cycle3), findViewById(R.id.day_cycle4), findViewById(R.id.day_cycle5), findViewById(R.id.day_cycle6))
        var select_days : MutableList<Boolean> = MutableList(7) { false }

        for (day in 0..6){
            day_cycles[day].setOnClickListener{
                if(select_days[day]){
                    select_days[day] = false
                    day_cycles[day].setBackgroundResource(R.drawable.circle_default)
                }else{
                    select_days[day] = true
                    day_cycles[day].setBackgroundResource(R.drawable.circle_select)
                }
            }
        }

        val btn_schedule_create : Button = findViewById(R.id.btn_medication_create)
        btn_schedule_create.setOnClickListener{
            var startHour = start_time_set.getHour() + if(start_time_set.isAm) 12 else 0
            var endHour = end_time_set.getHour() + if(end_time_set.isAm) 12 else 0
            if(startHour>endHour || (startHour==endHour && !(start_time_set.getMinute() == false && end_time_set.getMinute() == true))){
                Toast.makeText(this, "\"마감 시간\"을 \"시작 시간\" 보다 더 뒤에 설정해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                // 생성 결과 서버로 보내도록 작성
                finish()
            }
        }
    }
}