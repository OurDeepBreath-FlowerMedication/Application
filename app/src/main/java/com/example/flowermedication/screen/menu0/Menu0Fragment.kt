package com.example.flowermedication.screen.menu0

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import com.example.flowermedication.TodaySchedule
import com.example.flowermedication.get_data.getTodaySchedule
import kotlinx.coroutines.launch

class Menu0Fragment : Fragment() {
    private lateinit var scheduleAdapter : ScheduleAdapter
    private lateinit var testView : TextView
    private lateinit var flowerProgress : ImageView

    var todaySchedules = mutableListOf<TodaySchedule>()
    var flowerList = listOf(R.drawable.flower_0, R.drawable.flower_1, R.drawable.flower_2, R.drawable.flower_3,
                            R.drawable.flower_4, R.drawable.flower_5, R.drawable.flower_6)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_menu0, container, false)


        testView = view.findViewById(R.id.progress)
        flowerProgress = view.findViewById(R.id.flowerProgress)
        // 스케줄 리사이클 뷰
        var scheduleView: RecyclerView = view.findViewById(R.id.schedules)
        scheduleAdapter = ScheduleAdapter()
        scheduleView.adapter = scheduleAdapter
        scheduleView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val get : MutableList<TodaySchedule> = getTodaySchedule();

            todaySchedules.clear()  // 기존 데이터를 지우고
            todaySchedules.addAll(get)  // 새로운 데이터를 추가

            scheduleAdapter.updateData(get)

            val allCount : Int = todaySchedules.size
            val doneCount : Int = todaySchedules.count{it.isDone}

            testView.text = doneCount.toString() + "/" + allCount.toString();

            flowerProgress.setImageResource(flowerList[((doneCount.toDouble()/allCount)*6).toInt()])
        }

    }
}