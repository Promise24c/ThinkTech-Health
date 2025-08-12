package com.example.healthapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.model.Medication

class MedicationAdapter(private var medications: List<Medication>) :
    RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.medication_list_item, parent, false)
        return MedicationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        val medication = medications[position]
        holder.bind(medication)
    }

    override fun getItemCount() = medications.size

    fun updateData(newMedications: List<Medication>) {
        medications = newMedications
        notifyDataSetChanged()
    }

    class MedicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.medication_name)
        private val dosageTextView: TextView = itemView.findViewById(R.id.dosage)
        private val frequencyTextView: TextView = itemView.findViewById(R.id.frequency)
        private val timeTextView: TextView = itemView.findViewById(R.id.time)
        private val stockTextView: TextView = itemView.findViewById(R.id.stock)

        fun bind(medication: Medication) {
            nameTextView.text = medication.name
            dosageTextView.text = "Dosage: ${medication.dosage}"
            frequencyTextView.text = "Frequency: ${medication.frequency}"
            timeTextView.text = "Time: ${medication.time}"
            stockTextView.text = "Stock: ${medication.stock}"
        }
    }
}
