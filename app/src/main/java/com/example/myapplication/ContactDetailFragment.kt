package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.data.Contact
import com.example.myapplication.databinding.FragmentContactDetailBinding

private lateinit var binding:FragmentContactDetailBinding

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
          Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()


        }
        binding.callBtnDetail.setOnClickListener {
            Toast.makeText(context, "call", Toast.LENGTH_SHORT).show()
            val callIntent = Intent(Intent.ACTION_DIAL)
            startActivity(callIntent)
        }

        val selectedContact = arguments?.getParcelable<Contact>("selectedContact")

        binding.nameDetail.text = selectedContact?.name
        binding.phoneNumberDetail.text = selectedContact?.phone
        binding.emailDetail.text = selectedContact?.email

        return binding.root
    }
}