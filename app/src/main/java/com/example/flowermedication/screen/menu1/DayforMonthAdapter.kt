package com.example.flowermedication.screen.menu1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import com.example.flowermedication.DayItem
import com.example.flowermedication.DetailItem

class DayforMonthAdapter(private val items: MutableList<DayItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val expandedState = mutableMapOf<Int, Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu1_parent_item, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position / 2]
        val isExpanded = expandedState[position] ?: false
        (holder as ParentViewHolder).bind(item, isExpanded) {
            expandedState[position] = !isExpanded
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ParentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateText: TextView = view.findViewById(R.id.dateText)
        private val progressText: TextView = view.findViewById(R.id.progressText)
        private val expandArrow: ImageView = view.findViewById(R.id.expandArrow)
        private val child_item : RecyclerView = view.findViewById(R.id.child_item)

        fun bind(item: DayItem, isExpanded: Boolean, onClick: () -> Unit) {
            dateText.text = item.date
            progressText.text = item.progress
            expandArrow.rotation = if (isExpanded) 180f else 0f
            child_item.visibility = if (isExpanded) View.VISIBLE else View.GONE
            child_item.layoutManager = LinearLayoutManager(itemView.context)
            child_item.adapter = DetailAdapter(item.details)
            expandArrow.setOnClickListener {
                onClick()
            }
        }
    }
}

class DetailAdapter(private val items: MutableList<DetailItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu1_child_item, parent, false)
        return DetailAdapter.ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DetailAdapter.ChildViewHolder).bind(items[position].activity, items[position].time)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ChildViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateText: TextView = view.findViewById(R.id.taskText)
        private val timeText: TextView = view.findViewById(R.id.timeText)

        fun bind(activity: String, time: String) {
            dateText.text = activity
            timeText.text = time
        }
    }
}
