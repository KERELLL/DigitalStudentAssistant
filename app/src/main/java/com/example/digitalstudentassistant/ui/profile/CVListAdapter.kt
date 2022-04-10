package com.example.digitalstudentassistant.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalstudentassistant.R
import com.example.digitalstudentassistant.data.database.ProjectEntity
import com.example.digitalstudentassistant.domain.models.CV
import com.example.digitalstudentassistant.ui.projects.ProjectsListAdapter

class CVListAdapter(val click: (Int) -> Unit) : RecyclerView.Adapter<CVListAdapter.ViewHolder>() {

    var cVList: MutableList<CV> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cv, parent, false)
        return CVListAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = cVList[position].nameCV
        holder.deleteButton.setOnClickListener {
            cVList.removeAt(position)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return cVList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.cVTitleTextView)
        val deleteButton : ImageView = itemView.findViewById(R.id.deleteButton)
    }
}