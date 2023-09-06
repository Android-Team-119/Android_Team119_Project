package com.example.myapplication

import com.example.myapplication.data.Contact

interface DataUpdateListener {
    fun onDataUpdated(Contact: Contact)
}