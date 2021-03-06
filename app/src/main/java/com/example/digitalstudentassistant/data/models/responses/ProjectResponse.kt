package com.example.digitalstudentassistant.data.models.responses

data class ProjectResponse(
    val id: String,
    val title: String,
    val description: String,
    val communication: String,
    var creatorId: String,
    var tags: List<TagResponse>
)
