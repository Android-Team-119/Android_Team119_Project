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
            Contact(R.drawable.contactlistfragment_sample1_png, "이름1", "", "", false),
            Contact(R.drawable.contactlistfragment_sample2_png, "이름2", "", "", false),
            Contact(R.drawable.contactlistfragment_sample3_png, "이름3", "", "", false),
            Contact(R.drawable.contactlistfragment_sample4_png, "이름4", "", "", false),
            Contact(R.drawable.contactlistfragment_sample5_png, "이름5", "", "", false),
            Contact(R.drawable.contactlistfragment_sample6_png, "이름6", "", "", false),
            Contact(R.drawable.contactlistfragment_sample7_png, "이름7", "", "", false),
            Contact(R.drawable.contactlistfragment_sample8_png, "이름8", "", "", false),
            Contact(R.drawable.contactlistfragment_sample9_png, "이름9", "", "", false),
            Contact(R.drawable.contactlistfragment_sample10_png, "이름10", "", "", false),
        )

        val adapter = ContactAdapter(contactList)
        recyclerView.adapter = adapter

        return view

    }
}
