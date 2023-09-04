package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Contact
import com.example.myapplication.databinding.ContactlistFragmentBinding

class ContactListFragment : Fragment() {

    private lateinit var binding: ContactlistFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ContactlistFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        // RecyclerView 초기화 및 어댑터 설정
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val contactList = mutableListOf(
            Contact(null, "이름1", "", "", false),
            Contact(null, "이름2", "", "", false),
            Contact(null, "이름3", "", "", false),
            Contact(null, "이름4", "", "", false),
            Contact(null, "이름5", "", "", false),
            Contact(null, "이름6", "", "", false),
            Contact(null, "이름7", "", "", false),
            Contact(null, "이름8", "", "", false),
            Contact(null, "이름9", "", "", false),
            Contact(null, "이름10", "", "", false),
        )

        val adapter = ContactAdapter(contactList)
        recyclerView.adapter = adapter

        return view

    }
}
