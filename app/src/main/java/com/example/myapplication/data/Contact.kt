package com.example.myapplication.data

import android.net.Uri

data class Contact(
    val image : Uri,
    val name : String,
    val phone : String,
    val email : String,
    val isLike : Boolean
)
