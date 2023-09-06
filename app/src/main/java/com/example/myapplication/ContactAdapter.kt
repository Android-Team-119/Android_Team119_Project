package com.example.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Contact
import com.example.myapplication.data.ContactManager
import com.example.myapplication.databinding.ContactlistItemBinding
import com.example.myapplication.databinding.ContactlistItemGridBinding

class ContactAdapter() :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    var contactList = ContactManager.getContactList().toMutableList()

    fun addcontact(contact: Contact){
        contactList.add(contact)
        Log.d("it","$contactList")
        notifyDataSetChanged()
    }


    fun deleteContact(phone: String){
        ContactManager.deleteContactById(phone)
        notifyDataSetChanged()
    }

    fun updateContact(updatedContact: Contact) {
        val position = contactList.indexOfFirst { it.phone == updatedContact.phone }
        if (position != -1) {
            contactList[position] = updatedContact
            notifyItemChanged(position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactlistItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("this","")
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
        contactDelete(holder,position)
        favClicked(holder,position)

    }

    override fun getItemCount(): Int {
        Log.d("getItemCount","${contactList.size}")
        return contactList.size
    }
    fun contactDelete(holder: ViewHolder, position: Int){

        holder.itemView.setOnLongClickListener{
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("삭제")
            builder.setMessage("삭제하시겠습니까?")
            val listener = object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when(p1){
                        DialogInterface.BUTTON_POSITIVE ->{
                            deleteContact(contactList[position].phone)
                            Log.d("it","$contactList")
                            return
                        }
                        DialogInterface.BUTTON_NEGATIVE ->
                            return
                    }
                }
            }
            builder.setNegativeButton("취소",listener)
            builder.setPositiveButton("삭제",listener)
            builder.show()

            false
        }
    }
    fun favClicked(holder:ViewHolder,position:Int){
        val context = holder.itemView.context
        holder.favbtn.setOnClickListener{
            if(contactList[position].isLike == false){
                val img = ContextCompat.getDrawable(context, R.drawable.contactlistfragment_likebutton_image)
                holder.favbtn.setImageDrawable(img)
                ContactManager.updateIsLike(position,false)
                Log.d("test","${contactList}")
            }else{
                val img = ContextCompat.getDrawable(context, R.drawable.contactlistfragment_unlikebutton_image)
                holder.favbtn.setImageDrawable(img)
                ContactManager.updateIsLike(position,true)
                Log.d("test","${contactList}")
            }

        }

    }

    inner class ViewHolder(private val binding: ContactlistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val favbtn = binding.likeImageButton



        fun bind(contact: Contact) {
//            binding.profileImageView.setImageResource(contact.image)
            binding.nameTextView.text = contact.name
        }
    }

}