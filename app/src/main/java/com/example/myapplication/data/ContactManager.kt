package com.example.myapplication.data

object ContactManager {
    private val contactList = mutableListOf<Contact>(
        Contact(null, "이름1", "1", "1", false),
        Contact(null, "이름2", "2", "2", false),
        Contact(null, "이름3", "3", "3", false),
        Contact(null, "이름4", "4", "4", false),
        Contact(null, "이름5", "5", "5", false),
        Contact(null, "이름6", "6", "6", false),
        Contact(null, "이름7", "7", "7", false),
        Contact(null, "이름8", "8", "8", false),
        Contact(null, "이름9", "9", "9", false),
        Contact(null, "이름10", "10", "10", false),
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

