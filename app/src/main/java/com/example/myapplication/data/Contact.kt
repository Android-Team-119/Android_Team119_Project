package com.example.myapplication.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val image : Uri?,
    val name : String,
    val phone : String,
    val email : String,
    var isLike : Boolean
) : Parcelable