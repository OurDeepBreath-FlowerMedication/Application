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
import com.example.flowermedication.get_data.getMedication

class AdapterMedication(val position: Int): RecyclerView.Adapter<MedicationView>() {
    var medications : MutableList<Medication> =  getMedication(position)

    fun updateData(){
        medications = getMedication(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.menu3_medication, parent, false)
        return MedicationView(view)
    }

    override fun onBindViewHolder(holder: MedicationView, position: Int) {
        holder.setMedication(medications[position])
    }

    override fun getItemCount(): Int {
        return medications.size
    }
}

class MedicationView(val itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setMedication(medication: Medication){
        val medication_name_text : TextView = itemView.findViewById(R.id.medication_name_text)
        medication_name_text.text = medication.medication_name

        val btn_delete : ImageButton = itemView.findViewById(R.id.btn_delete)
        btn_delete.setOnClickListener{
            // 삭제 처리하는 코드 추가
            Toast.makeText(itemView.context,"${medication.medication_name} 복용을 삭제합니다.", Toast.LENGTH_SHORT).show()
        }
    }
}