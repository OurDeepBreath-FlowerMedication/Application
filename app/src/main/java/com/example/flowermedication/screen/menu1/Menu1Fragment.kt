package com.example.flowermedication.screen.menu1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import com.example.flowermedication.DayItem
import com.example.flowermedication.DetailItem

class Menu1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_menu1, container, false)

        val monthListManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val monthListAdapter = AdapterMonth()

        val calendar_custom : RecyclerView = view.findViewById(R.id.calendar_custom)
        calendar_custom.apply {
            layoutManager = monthListManager
            adapter = monthListAdapter
            scrollToPosition(Int.MAX_VALUE/2)
        }
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(calendar_custom)

        val tasksForMonth: RecyclerView = view.findViewById(R.id.tasksForMonth)
        tasksForMonth.layoutManager = LinearLayoutManager(requireContext())
        tasksForMonth.adapter = DayforMonthAdapter(getTasks())

        return view
    }

    // 나중에 서버에서 받아오는 데이터로 변경
    fun getTasks():MutableList<DayItem>{
        val data : MutableList<DayItem> = mutableListOf()
        val data2 : MutableList<DetailItem> = mutableListOf()

        data2.add(DetailItem("아침약 섭취", "10:31"))
        data2.add(DetailItem("산책", "13:15"))
        data.add(DayItem("7월 1일", "7/7", data2))
        data.add(DayItem("7월 2일", "5/7", data2))
        data.add(DayItem("7월 3일", "6/7", data2))

        return data
    }

}