package com.example.flowermedication.screen.menu3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.Medication
import com.example.flowermedication.R
import com.example.flowermedication.get_data.getMedication
import com.example.flowermedication.screen.menu2.Menu2Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class Menu3Fragment(val day:Int) : Fragment() {

    var adapterDay : AdapterDay ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu3, container, false)

        // 요일별 스케줄을 설정하기 위한 리사이클 뷰
        val day_list : RecyclerView = view.findViewById(R.id.day)
        day_list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapterDay = AdapterDay(this)
        day_list.adapter = adapterDay

        day_list.scrollToPosition(day)

        day_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        snap.attachToRecyclerView(day_list)

        /////////


        val btn_add_new_medication : FloatingActionButton = view.findViewById(R.id.btn_add_new_medication)
        btn_add_new_medication.setOnClickListener{
            startActivity(Intent(view.context, AddMedication::class.java))
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val get : MutableList<Medication> = getMedication();
            adapterDay?.updateData(get)
        }
    }

    fun refreashFragment(select_day : Int){
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, Menu3Fragment(select_day))
        fragmentTransaction.commit()
    }
}