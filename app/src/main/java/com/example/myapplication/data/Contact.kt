package com.example.myapplication.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val image : Uri?,
    var name : String,
    var phone : String,
    var email : String,
    var isLike : Boolean
) : Parcelable