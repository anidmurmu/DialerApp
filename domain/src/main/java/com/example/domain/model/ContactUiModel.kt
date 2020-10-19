package com.example.domain.model

data class ContactUiModel(
    var contactNumber: String,
    var fullName: String,
    var email: String,
    var isBlocked: Boolean = false
)