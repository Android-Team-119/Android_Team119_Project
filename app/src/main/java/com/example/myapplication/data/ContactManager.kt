package com.example.myapplication.data


import android.net.Uri
import com.example.myapplication.R


object ContactManager {

    private val imageUri1: Uri? = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample1_png)
    val imageUri2 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample2_png)
    val imageUri3 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample3_png)
    val imageUri4 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample4_png)
    val imageUri5 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample5_png)
    val imageUri6 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample6_png)
    val imageUri7 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample7_png)
    val imageUri8 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample8_png)
    val imageUri9 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample9_png)
    val imageUri10 = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + R.drawable.contactlistfragment_sample10_png)

//    val contentResolver : ContentResolver = this.contentResolver

    //예제 데이터
    private val contactList = mutableListOf<Contact>(
        Contact(imageUri1, "이름1", "1", "1", false),
        Contact(imageUri2, "이름2", "2", "2", false),
        Contact(imageUri3, "이름3", "3", "3", false),
        Contact(imageUri4, "이름4", "4", "4", false),
        Contact(imageUri5, "이름5", "5", "5", false),
        Contact(imageUri6, "이름6", "6", "6", false),
        Contact(imageUri7, "이름7", "7", "7", false),
        Contact(imageUri8, "이름8", "8", "8", false),
        Contact(imageUri9, "이름9", "9", "9", false),
        Contact(imageUri10, "이름10", "10", "10", false),
    )


    //개인 정보 객체
    var user = Contact(imageUri3, "이충환", "010", "1", false)

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

    fun updateImageUriByPhone(phone: String, newImageUri: Uri) {
        val contactToUpdate = findContactByPhone(phone)
        if (contactToUpdate != null) {
            contactToUpdate.image = newImageUri
        }
    }

}

