package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Contact
import com.example.myapplication.data.ContactManager
import com.example.myapplication.databinding.ContactlistItemBinding

class ContactAdapter() :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private val contactList = ContactManager.getContactList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactlistItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("selectedContact",contact)

            val contactDetailFragment = ContactDetailFragment()

            contactDetailFragment.arguments = bundle

            // Fragment 전환
            val fragmentManager = (it.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frag_container, contactDetailFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ViewHolder(private val binding: ContactlistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
//            binding.profileImageView.setImageResource(contact.image)
            binding.nameTextView.text = contact.name
        }
    }
}