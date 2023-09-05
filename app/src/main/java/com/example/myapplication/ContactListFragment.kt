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

        val adapter = ContactAdapter()
        recyclerView.adapter = adapter

        return view

    }
}
