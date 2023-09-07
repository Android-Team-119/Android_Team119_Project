package com.example.myapplication

import com.example.myapplication.data.Contact

interface DataUpdateListener {
    fun onDataUpdated(contact:Contact)
    fun updateContact(contact: Contact, position:Int)
}