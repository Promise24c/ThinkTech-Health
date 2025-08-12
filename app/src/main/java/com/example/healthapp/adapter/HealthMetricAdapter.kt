package com.example.healthapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.model.HealthMetric
import java.text.SimpleDateFormat
import java.util.*

class HealthMetricAdapter(private var metrics: List<HealthMetric>) :
    RecyclerView.Adapter<HealthMetricAdapter.HealthMetricViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthMetricViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.health_metric_list_item, parent, false)
        return HealthMetricViewHolder(view)
    }

    override fun onBindViewHolder(holder: HealthMetricViewHolder, position: Int) {
        val metric = metrics[position]
        holder.bind(metric)
    }

    override fun getItemCount() = metrics.size

    fun updateData(newMetrics: List<HealthMetric>) {
        metrics = newMetrics
        notifyDataSetChanged()
    }

    class HealthMetricViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.date)
        private val bloodPressureTextView: TextView = itemView.findViewById(R.id.blood_pressure)
        private val bloodSugarTextView: TextView = itemView.findViewById(R.id.blood_sugar)
        private val heartRateTextView: TextView = itemView.findViewById(R.id.heart_rate)
        private val notesTextView: TextView = itemView.findViewById(R.id.notes)

        fun bind(metric: HealthMetric) {
            dateTextView.text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(metric.date))
            bloodPressureTextView.text = "Blood Pressure: ${metric.bloodPressure}"
            bloodSugarTextView.text = "Blood Sugar: ${metric.bloodSugar}"
            heartRateTextView.text = "Heart Rate: ${metric.heartRate}"
            notesTextView.text = "Notes: ${metric.notes}"
        }
    }
}
