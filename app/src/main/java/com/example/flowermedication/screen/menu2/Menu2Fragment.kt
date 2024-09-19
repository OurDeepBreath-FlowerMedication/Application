package com.example.flowermedication.screen.menu2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.DaySchedule
import com.example.flowermedication.R
import com.example.flowermedication.device_init.DeviceRegistration
import com.example.flowermedication.get_data.getDaySchedule
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.util.Calendar

class Menu2Fragment(private val day : Int) : Fragment() {

    private lateinit var adapter_day : AdapterDay
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_menu2, container, false)

        // 요일별 스케줄을 설정하기 위한 리사이클 뷰
        val day_schedules: RecyclerView = view.findViewById(R.id.day_schedules)
        day_schedules.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter_day = AdapterDay(this)
        day_schedules.adapter = adapter_day

        day_schedules.scrollToPosition(day)

        day_schedules.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 스크롤 위치에 따라 뷰 상태 변경
                val position =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                val dayIds = arrayOf(
                    R.id.day_cycle0,
                    R.id.day_cycle1,
                    R.id.day_cycle2,
                    R.id.day_cycle3,
                    R.id.day_cycle4,
                    R.id.day_cycle5,
                    R.id.day_cycle6
                )

                for (dayId in dayIds) {
                    view.findViewById<View>(dayId)
                        .setBackgroundResource(R.drawable.circle_default)
                }

                var select_day: View = view.findViewById(dayIds[position])
                select_day.setBackgroundResource(R.drawable.circle_select)

            }
        })

        // 스냅으로 페이지 바뀌도록
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(day_schedules)

        val btn_add_new_schedule: FloatingActionButton =
            view.findViewById(R.id.btn_add_new_schedule)
        btn_add_new_schedule.setOnClickListener {
            startActivity(Intent(view.context, AddSchedule::class.java))
        }


        return view
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            val get : MutableList<DaySchedule> = getDaySchedule();

            adapter_day.updateData(get)
        }
    }

    fun refreashFragment(select_day : Int){
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, Menu2Fragment(select_day))
        fragmentTransaction.commit()
    }

}      