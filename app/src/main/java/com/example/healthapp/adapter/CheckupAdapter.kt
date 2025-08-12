package com.example.healthapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.model.Checkup
import java.text.SimpleDateFormat
import java.util.*

class CheckupAdapter(private var checkups: List<Checkup>) :
    RecyclerView.Adapter<CheckupAdapter.CheckupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckupViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.checkup_list_item, parent, false)
        return CheckupViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckupViewHolder, position: Int) {
        val checkup = checkups[position]
        holder.bind(checkup)
    }

    override fun getItemCount() = checkups.size

    fun updateData(newCheckups: List<Checkup>) {
        checkups = newCheckups
        notifyDataSetChanged()
    }

    class CheckupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val doctorNameTextView: TextView = itemView.findViewById(R.id.doctor_name)
        private val locationTextView: TextView = itemView.findViewById(R.id.location)
        private val dateTextView: TextView = itemView.findViewById(R.id.date)
        private val notesTextView: TextView = itemView.findViewById(R.id.notes)

        fun bind(checkup: Checkup) {
            doctorNameTextView.text = checkup.doctorName
            locationTextView.text = "Location: ${checkup.location}"
            dateTextView.text = "Date: ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(checkup.date))}"
            notesTextView.text = "Notes: ${checkup.notes}"
        }
    }
}
