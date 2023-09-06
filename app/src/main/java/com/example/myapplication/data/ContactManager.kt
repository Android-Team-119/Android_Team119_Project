package com.example.myapplication.data

import android.R
import android.net.Uri


object ContactManager {

    val imageuri1: Uri = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + com.example.myapplication.R.drawable.contactlistfragment_sample10_png)
    val imageuri2 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.ic_delete)
    //예제 데이터
    private val contactList = mutableListOf<Contact>(
        Contact(imageuri1, "이름1", "1", "1", false),
        Contact(imageuri1, "이름2", "2", "2", false),
        Contact(imageuri1, "이름3", "3", "3", false),
        Contact(imageuri1, "이름4", "4", "4", false),
        Contact(imageuri1, "이름5", "5", "5", false),
        Contact(imageuri2, "이름6", "6", "6", false),
        Contact(imageuri2, "이름7", "7", "7", false),
        Contact(imageuri2, "이름8", "8", "8", false),
        Contact(imageuri2, "이름9", "9", "9", false),
        Contact(imageuri2, "이름10", "10", "10", false),
    )



    //개인 정보 객체
    var user = Contact(null, "이충환", "010", "1", false)

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
    fun getContactList(): MutableList<Contact> {
        return contactList
    }

    //user 데이터 업데이트
    fun updateUser(updatedUser: Contact) {
        user = updatedUser
    }
    fun updateIsLike(position:Int, like:Boolean) {
        contactList[position].isLike = !like
    }

    fun getPhoneByPosition(position: Int) : String {
        return contactList[position].phone
    }

}

