package com.example.digitalstudentassistant.data.models.responses.jwt

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    val acr: String,
    val address: Any?,
    @SerializedName("allowed-origins")
    val allowed_origins: List<String>,
    val at_hash: Any?,
    val aud: String,
    val auth_time: Any?,
    val authorization: Any?,
    val azp: String,
    val birthdate: Any?,
    val c_hash: Any?,
    val claims_locales: Any?,
    val cnf: Any?,
    val email: String?,
    val email_verified: Boolean,
    val exp: Int,
    val family_name: Any?,
    val gender: Any?,
    val given_name: String,
    val iat: Int,
    val iss: String,
    val jti: String,
    val locale: Any?,
    val middle_name: Any?,
    val name: String,
    val nbf: Any?,
    val nickname: Any?,
    val nonce: Any?,
    val phone_number: Any?,
    val phone_number_verified: Any?,
    val picture: Any?,
    val preferred_username: String,
    val profile: Any?,
    val realm_access: RealmAccess,
    val resource_access: ResourceAccess,
    val s_hash: Any?,
    val scope: String,
    val session_state: String,
    val sid: String,
    val sub: String,
    val telegram: String,
    @SerializedName("trusted-certs")
    val trusted_certs: Any?,
    val typ: String,
    val updated_at: Any?,
    val website: Any?,
    val zoneinfo: Any?
)