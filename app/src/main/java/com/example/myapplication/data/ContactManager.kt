package com.example.myapplication.data

object ContactManager {
    private val contactList = mutableListOf<Contact>()

    fun addContact(contact: Contact) {
        contactList.add(contact)
    }

    fun updateContact(contact: Contact) {
        val existingContact = findContactByPhone(contact.phone)
        if (existingContact != null) {
            contactList.remove(existingContact)
            contactList.add(contact)
        }
    }

    fun deleteContactById(phone: String) {
        val contactToRemove = findContactByPhone(phone)
        if (contactToRemove != null) {
            contactList.remove(contactToRemove)
        }
    }

    fun findContactByPhone(phone: String): Contact? {
        return contactList.find { it.phone == phone }
    }

}

