package com.example.flowermedication.screen.menu3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.Medication
import com.example.flowermedication.R
import com.example.flowermedication.get_data.delMedication
import com.example.flowermedication.get_data.getMedication

class AdapterMedication(val position: Int, val day:Int, val fragment: Menu3Fragment): RecyclerView.Adapter<MedicationView>() {
    var medications : MutableList<Medication> =  mutableListOf()

    fun updateData(medication_list : MutableList<Medication>){
        medications.clear()
        medications.addAll(medication_list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.menu3_medication, parent, false)
        return MedicationView(view)
    }

    override fun onBindViewHolder(holder: MedicationView, position: Int) {
        holder.setMedication(medications[position], day, fragment)
    }

    override fun getItemCount(): Int {
        return medications.size
    }
}

class MedicationView(val itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setMedication(medication: Medication, day:Int, fragment: Menu3Fragment){
        val medication_name_text : TextView = itemView.findViewById(R.id.medication_name_text)
        medication_name_text.text = medication.medication_name

        val btn_delete : ImageButton = itemView.findViewById(R.id.btn_delete)
        btn_delete.setOnClickListener{
            delMedication(medication.id)
            Toast.makeText(itemView.context,"\"${medication.medication_name}\" 복용을 삭제합니다.", Toast.LENGTH_SHORT).show()
            fragment.refreashFragment(day)
        }
    }
}