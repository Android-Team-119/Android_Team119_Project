package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.data.Contact
import com.example.myapplication.databinding.FragmentContactGridBinding

class ContactGridFragment : Fragment() {

    private lateinit var gridBinding: FragmentContactGridBinding
    private val gridAdapter by lazy{
        ContactAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gridBinding = FragmentContactGridBinding.inflate(inflater, container, false)
        return gridBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() = with(gridBinding){
        // RecyclerView 초기화 및 어댑터 설정
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        recyclerView.adapter = gridAdapter
//        val recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        val adapter = ContactAdapter()
//        recyclerView.adapter = adapter
    }

    fun setContact(contact: Contact){
        gridAdapter.addContact(contact)
    }

}