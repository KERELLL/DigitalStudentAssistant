package com.example.digitalstudentassistant.ui.project

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.digitalstudentassistant.R
import com.example.digitalstudentassistant.data.database.ProjectEntity
import com.example.digitalstudentassistant.databinding.FragmentProjectBinding
import com.example.digitalstudentassistant.domain.models.Project
import com.example.digitalstudentassistant.ui.textChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import java.util.*


class ProjectFragment : Fragment() {


    private lateinit var binding: FragmentProjectBinding
    private lateinit var projectViewModel: ProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjectBinding.inflate(layoutInflater)
        projectViewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpDatePicker(binding.deadlineProjectFromDateEditText)
        setUpDatePicker(binding.deadlineProjectToDateEditText)
        setUpDatePicker(binding.deadlineTeamFromDateEditText)
        setUpDatePicker(binding.deadlineTeamToDateEditText)
        setUpStatus()
        addProjectValidation()

        binding.createButton.setOnClickListener {
            createProject()
        }
    }


    private fun createProject(){
        val name = binding.projectNameEditText.text.toString()
        val purpose = binding.purposeEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()
        val deadlineDate = binding.deadlineProjectFromDateEditText.text.toString()
        val participantsNumber = binding.numberOfPeopleEditText.text.toString()
        val status = binding.statusEditText.text.toString()
        val recordingPeriod = "23.42.1"
        if(inputCheck(name, purpose, description)){
            val project = ProjectEntity(name, purpose, description, deadlineDate, participantsNumber.toInt(), recordingPeriod, status)
            projectViewModel.addProject(project)
        }
    }

    private fun setUpStatus(){
        val status = listOf("Создан", "Завершен", "Есть вакансии")
        val adapter = ArrayAdapter(requireContext(), R.layout.status_list_item, status)
        binding.statusEditText.setAdapter(adapter)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpDatePicker(dateEditText: EditText){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        dateEditText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(), R.style.DialogTheme, DatePickerDialog.OnDateSetListener{
                    view, year, monthOfYear, dayOfMonth ->
                var dayOfMonthStr = "$dayOfMonth"
                var monthOfYearStr = "${monthOfYear+1}"
                if(dayOfMonth < 10)
                    dayOfMonthStr = "0$dayOfMonth"
                if(monthOfYear < 10)
                    monthOfYearStr = "0$monthOfYear"
                dateEditText.setText("$dayOfMonthStr.${monthOfYearStr}.$year")
            }, year, month, day )
            datePickerDialog.show()
        }
    }

    private fun inputCheck(name : String, purpose: String, description: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(purpose) && TextUtils.isEmpty(description))
    }

    @ExperimentalCoroutinesApi
    private fun addProjectValidation(){
        binding.projectNameEditText.textChanges()
            .debounce(300)
            .onEach {
                if(binding.projectNameEditText.text.isNullOrEmpty()){

                }else{
                    if(binding.projectNameEditText.text!!.length < 4 || binding.projectNameEditText.text!!.length > 30){
                        binding.projectNameContainer.errorContentDescription = "Hello"
                    }
                }
            }.launchIn(GlobalScope)
    }
}