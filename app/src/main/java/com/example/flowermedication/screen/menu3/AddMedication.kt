package com.example.flowermedication.screen.menu3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import com.example.flowermedication.R

class AddMedication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu3_add_medication)

        val custom_schedule_edit : EditText = findViewById(R.id.custom_schedule_edit)
        val mealTime : RadioGroup = findViewById(R.id.mealTime)
        val interval : RadioGroup = findViewById(R.id.interval)
        val useCheck : Switch = findViewById(R.id.useCheck)

        useCheck.isChecked = true
        useCheck.setOnClickListener{
            if(useCheck.isChecked){
                useCheck.text = "복욕기 사용"
            }else{
                useCheck.text = "복욕기 "
            }
        }

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

        val btn_medication_create : Button = findViewById(R.id.btn_medication_create)
        btn_medication_create.setOnClickListener{
            val getMealID = mealTime.checkedRadioButtonId
            val getIntervalID = interval.checkedRadioButtonId
            if(custom_schedule_edit.text.toString().isEmpty()){
                Toast.makeText(this, "복용약의 명칭을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else if(getMealID == -1){
                Toast.makeText(this, "복용 시간을 선택해 주세요.", Toast.LENGTH_SHORT).show()
            }else if(getIntervalID == -1){
                Toast.makeText(this, "복용 간격을 선택해 주세요.", Toast.LENGTH_SHORT).show()
            }else{
                // 서버에 데이터 전송
                finish()
            }
        }
    }
}