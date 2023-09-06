package com.example.myapplication.data

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Log

object ContactManager {

    //예제 데이터
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

    @SuppressLint("DiscouragedApi")
    fun getDrawableUri(context: Context, drawableName: String): Uri? {
        val resourceId = context.resources.getIdentifier(
            drawableName,
            "drawable",
            context.packageName
        )

        if (resourceId == 0) {
            Log.e("DrawableUriParser", "Drawable not found with name: $drawableName")
            return null
        }

        return Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(context.packageName)
            .path(resourceId.toString())
            .build()
    }
}

