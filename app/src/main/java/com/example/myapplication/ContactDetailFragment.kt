package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import com.example.myapplication.data.Contact
import com.example.myapplication.data.ContactManager
import com.example.myapplication.databinding.FragmentContactDetailBinding

private lateinit var binding:FragmentContactDetailBinding
private val contactAdapter = ContactAdapter()

class ContactDetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding = FragmentContactDetailBinding.inflate(layoutInflater)
        binding.messageBtnDetail.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val dialog = UpdateNumberDialog()

            // 다이얼로그(Dialog)에서 수정된 정보를 Fragment로 전달하는 콜백 설정
            dialog.testContact = object : UpdateNumberDialog.InputContact {
                override fun setContact(contact: Contact) {
                    // 수정된 정보를 받아서 처리
                    updateContactInfo(contact)
                }
            }

            dialog.show(fragmentManager, "UpdateNumberDialog")
        }
        binding.callBtnDetail.setOnClickListener {
            Toast.makeText(context, "call", Toast.LENGTH_SHORT).show()
            val callIntent = Intent(Intent.ACTION_DIAL)
            startActivity(callIntent)
        }

        val contact = Contact(null, "디폴트 네임", "010-0000-0000", "email@email.com", false)
        var selectedContact = arguments?.getParcelable<Contact>("selectedContact")

        if(selectedContact==null){
            selectedContact = contact
            binding.linearLayoutCallBtn.visibility = View.GONE
        }

        binding.nameDetail.text = selectedContact.name
        binding.phoneNumberDetail.text = selectedContact.phone
        binding.emailDetail.text = selectedContact.email

        return binding.root
    }

    fun updateContactInfo(updatedContact: Contact) {
        ContactManager.updateContact(updatedContact)

        binding.nameDetail.text = updatedContact.name
        binding.phoneNumberDetail.text = updatedContact.phone
        binding.emailDetail.text = updatedContact.email

        contactAdapter.updateContact(updatedContact)

    }


}