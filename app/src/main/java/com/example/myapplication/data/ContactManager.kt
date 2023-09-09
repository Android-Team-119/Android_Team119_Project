package com.example.myapplication.data


import android.net.Uri
import com.example.myapplication.R

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.util.Log

object ContactManager {

    private val imageUri1: Uri = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample1)
    private val imageUri2: Uri = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample2)
    private val imageUri3: Uri = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample3)
    private val imageUri4: Uri = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample4)
    private val imageUri5: Uri = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample5)

//    val contentResolver : ContentResolver = this.contentResolver

    //예제 데이터
    private val contactList = mutableListOf<Contact>(
        Contact(imageUri1, "비상 연락처", "010-1234-567", "ch901@naver.com", false),
        Contact(imageUri2, "매니저님", "010-1111-222", "ch901@naver.com", false),
        Contact(imageUri3, "튜터님", "010-1234-567", "ch901@naver.com", false),
        Contact(imageUri4, "내일 배움 캠프", "010-0000-987", "ch901@naver.com", false),
        Contact(imageUri5, "안주환", "010-1234-567", "ch901@naver.com", false),
        Contact(imageUri2, "이동희", "010-1111-222", "ch901@naver.com", false),
        Contact(imageUri3, "이슬비", "010-0000-000", "ch901@naver.com", false),
        Contact(imageUri4, "이충환", "010-1234-567", "ch901@naver.com", false),
        Contact(imageUri5, "조광희", "010-1234-567", "ch901@naver.com", false),
    )


    //개인 정보 객체
    var user = Contact(imageUri2, "내배캠", "010-1234-567", "ch901@naver.com", false)

    //전화번호 추가
    fun addContact(contact: Contact) {
        contactList.add(contact)
    }

    //전화번호 업데이트
    fun updateContact(contact: Contact) {
        val existingContact = findContactByPhone(contact.phone)
        if (existingContact != null) {
            existingContact.name = contact.name
            existingContact.phone = contact.phone
            existingContact.email = contact.email
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

    //position 값을 받아 해당 position의 전화번호를 리턴
    fun getPhoneByPosition(position: Int) : String {
        return contactList[position].phone
    }
    fun editContact(position:Int, contact: Contact){
        contactList[position] = contact
    }

    fun updateImageUriByPhone(phone: String, newImageUri: Uri) {
        val contactToUpdate = findContactByPhone(phone)
        if (contactToUpdate != null) {
            contactToUpdate.image = newImageUri
        }
    }

}

