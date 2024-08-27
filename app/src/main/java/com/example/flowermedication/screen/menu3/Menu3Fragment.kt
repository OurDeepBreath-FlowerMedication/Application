package com.example.flowermedication.screen.menu3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import com.example.flowermedication.screen.menu2.AddSchedule
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Menu3Fragment : Fragment() {

    var adapterMeal : AdapterMeal ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu3, container, false)

        val meal_list : RecyclerView = view.findViewById(R.id.meal_list)
        meal_list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterMeal = AdapterMeal()
        meal_list.adapter = adapterMeal

        val btn_add_new_medication : FloatingActionButton = view.findViewById(R.id.btn_add_new_medication)
        btn_add_new_medication.setOnClickListener{
            startActivity(Intent(view.context, AddMedication::class.java))
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        adapterMeal?.updateData()
    }
}