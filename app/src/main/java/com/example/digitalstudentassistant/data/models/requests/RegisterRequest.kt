    package com.example.digitalstudentassistant.data.models.requests

data class RegisterRequest(
    val email: String,
    val nickname: String,
    val phoneNumber : String,
    val firstname : String,
    val lastname : String,
    val surname : String?,
//    val interests : List<String>,
    val telegram: String,
    val password: String,
)