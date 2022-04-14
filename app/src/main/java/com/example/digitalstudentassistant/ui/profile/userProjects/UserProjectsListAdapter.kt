package com.example.digitalstudentassistant.ui.profile.userProjects

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalstudentassistant.R
import com.example.digitalstudentassistant.data.database.ProjectEntity
import com.example.digitalstudentassistant.data.models.responses.ProjectResponse
import com.example.digitalstudentassistant.domain.models.Project
import com.google.android.material.chip.Chip

class UserProjectsListAdapter (var context: Context, val click: (ProjectResponse) -> Unit) : RecyclerView.Adapter<UserProjectsListAdapter.ViewHolder>() {
    var projectsList: MutableList<ProjectResponse> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_projects, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.projectNameTextView.text = projectsList[position].title
        holder.projectDescriptionTextView.text = projectsList[position].description
        var tagsList = projectsList[position].tags
//        var tagsStringList = mutableListOf<String>()
//        for (tag in tagsList){
//            holder.projectStatusTextView.text = holder.projectStatusTextView.text.toString() + projectsList[position].tags. + "\n"
//        }
        holder.itemView.setOnClickListener {
            click.invoke(projectsList[position])
        }
        holder.likeChip.setOnClickListener {
            holder.likeChip.isSelected = !holder.likeChip.isSelected
            if(holder.likeChip.isSelected){
                holder.likeChip.text = (holder.likeChip.text.toString().toInt() + 1).toString()
                holder.likeChip.setChipIconResource(R.drawable.ic_thumb_up)
            }else{
                holder.likeChip.text = (holder.likeChip.text.toString().toInt() - 1).toString()
                holder.likeChip.setChipIconResource(R.drawable.ic_outline_thumb_up)
            }
        }
    }

    override fun getItemCount(): Int {
        return projectsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val projectNameTextView: TextView = itemView.findViewById(R.id.projectNameTextView)
        val projectStatusTextView: TextView = itemView.findViewById(R.id.statusTextView)
        val projectDescriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val likeChip: Chip = itemView.findViewById(R.id.likeChip)
    }
}