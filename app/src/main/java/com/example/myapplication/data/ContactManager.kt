package com.example.myapplication.data

object ContactManager {
    private val contactList = mutableListOf<Contact>(
        Contact(null, "이름1", "", "", false),
        Contact(null, "이름2", "", "", false),
        Contact(null, "이름3", "", "", false),
        Contact(null, "이름4", "", "", false),
        Contact(null, "이름5", "", "", false),
        Contact(null, "이름6", "", "", false),
        Contact(null, "이름7", "", "", false),
        Contact(null, "이름8", "", "", false),
        Contact(null, "이름9", "", "", false),
        Contact(null, "이름10", "", "", false),
    )

    //전화번호 추가
    fun addContact(contact: Contact) {
        contactList.add(contact)
    }

    //전화번호 업데이트
    fun updateContact(contact: Contact) {
        val existingContact = findContactByPhone(contact.phone)
        if (existingContact != null) {
            contactList.remove(existingContact)
            contactList.add(contact)
        }
    }

    //전화번호 삭제
    fun deleteContactById(phone: String) {
        val contactToRemove = findContactByPhone(phone)
        if (contactToRemove != null) {
            contactList.remove(contactToRemove)
        }
    }

    //전화번호 입력 시 Contact return
    fun findContactByPhone(phone: String): Contact? {
        return contactList.find { it.phone == phone }
    }

    //모든 연락처를 return
    fun getContactList(): List<Contact> {
        return contactList.toList()
    }

}

