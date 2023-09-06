package com.example.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Contact
import com.example.myapplication.data.ContactManager
import com.example.myapplication.databinding.ContactlistItemBinding
import com.example.myapplication.databinding.ContactlistItemGridBinding

class ContactAdapter(private val listType: Boolean = false) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    var contactList = ContactManager.getContactList().toMutableList()
    var contactList = ContactManager.getContactList()
    fun addcontact(contact: Contact){
        ContactManager.addContact(contact)
//        contactList.add(contact)
        notifyDataSetChanged()
    }
    fun deleteContact(phone:String){
        ContactManager.deleteContactById(phone)
//        contactList.remove(contact)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /*
        val binding = ContactlistItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)*/
        return if(!listType){
            val binding = ContactlistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
            ViewHolder(binding)
        }else{
            val gridBinding = ContactlistItemGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
            GridViewHolder(gridBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contact = contactList[position]
        if (holder is ViewHolder){
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
        }else if(holder is GridViewHolder){
            holder.gridbind(contact)
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
        }


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
    fun contactDelete(holder: GridViewHolder, position: Int){
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


    // 공통된 뷰홀더를 하나 더 만들고 난 후 이너클래스 뷰 홀더를 두개를 상속받아서 온 바인드 뷰홀더에서 공통 뷰홀더를 사용
    inner class ViewHolder(private val binding: ContactlistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val favbtn = binding.likeImageButton

        fun bind(contact: Contact) {
//            binding.profileImageView.setImageResource(contact.image)
            binding.nameTextView.text = contact.name
        }
    }

    inner class GridViewHolder(private val gridBinding: ContactlistItemGridBinding) :
        RecyclerView.ViewHolder(gridBinding.root) {
        fun gridbind(contact: Contact) {
//            binding.profileImageView.setImageResource(contact.image)
            gridBinding.nameGridTextview.text = contact.name
        }
    }

}