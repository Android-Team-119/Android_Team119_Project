package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Contact
import com.example.myapplication.databinding.ContactlistItemBinding

class ContactAdapter(private val contactList: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

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