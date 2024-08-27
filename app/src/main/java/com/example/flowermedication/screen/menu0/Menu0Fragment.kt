package com.example.flowermedication.screen.menu0

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import com.example.flowermedication.Schedule

class Menu0Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_menu0, container, false)
        // 스케줄 리사이클 뷰
        var scheduleView: RecyclerView = view.findViewById(R.id.schedules)
        var schedules:MutableList<Schedule> = loadData()
        val scheduleAdapter = ScheduleAdapter()

        scheduleAdapter.listData = schedules
        scheduleView.adapter = scheduleAdapter
        scheduleView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    // 나중에 서버에서 데이터를 가져오는 것으로 수정(임의 데이터 셋 생성)
    fun loadData() : MutableList<Schedule>{
        val data: MutableList<Schedule> = mutableListOf()
        data.add(Schedule("아침약 섭취", 2024, 8, 15, 10, 32, true))
        data.add(Schedule("산책", 2024, 8, 15, 10, 50, true))
        data.add(Schedule("오늘의 날씨 확인"))
        data.add(Schedule("방 청소"))
        data.add(Schedule("점심약 섭취"))
        data.add(Schedule("칼취 기원"))
        data.add(Schedule("핸드폰에 잘 뜨는지"))
        data.add(Schedule("확인중"))
        return data
    }
}