package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.Contact
import com.example.myapplication.data.ContactManager
import com.example.myapplication.databinding.ContactlistFragmentBinding

class ContactListFragment : Fragment() {

    private lateinit var binding: ContactlistFragmentBinding
//    private val listAdapter by lazy{
//        ContactAdapter()
//    }
    private var statusCheck = false

    private val listAdapter by lazy{
        ContactAdapter(listType = false)
    }
    private val listAdapterGrid by lazy{
        ContactAdapter(listType = true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ContactlistFragmentBinding.inflate(inflater, container, false)

        binding.run {
            listToolbar.run {
                // 제목 설정
                title = "도와줘요 119"

                inflateMenu(R.menu.main_item_toolbarmenu)

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.add_item -> {
                            val dialog = AddNumberDialog()
                            if(!statusCheck){
                                dialog.testContact = object: AddNumberDialog.InputContact{

                                    override fun setContect(contact: Contact) {
//                                        ContactManager.addContact(contact)//전역변수에 값 저장
                                        listAdapter.addcontact(contact)//listAdapter에 저장된 변수에 값 저장
//                                        listAdapterGrid.addcontact(contact)
                                    }
                                }
                            }else if(statusCheck){
                                dialog.testContact = object :AddNumberDialog.InputContact{
                                    override fun setContect(contact: Contact) {
//                                        ContactManager.addContact(contact)//전역변수에 값 저장
//                                        listAdapter.addcontact(contact)
                                        listAdapterGrid.addcontact(contact)//listAdapter에 저장된 변수에 값 저장
                                    }
                                }
                            }

                            dialog.show(childFragmentManager, "AddNumberDialog")
                            true
                        }
                        R.id.list_item -> {
                            initView()
                            statusCheck = false
                            true
                        }
                        R.id.grid_item -> {
                            initGridView()
                            statusCheck = true
                            true
                        }

                        else -> {
                            false
                        }
                    }
                }
            }
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }
    private fun initView() = with(binding){
        // RecyclerView 초기화 및 어댑터 설정
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = listAdapter

//        val recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        val adapter = ContactAdapter()
//        recyclerView.adapter = adapter
    }
    private fun initGridView() = with(binding){
        // RecyclerView 초기화 및 어댑터 설정
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        recyclerView.adapter = listAdapterGrid

//        val recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        val adapter = ContactAdapter()
//        recyclerView.adapter = adapter
    }


}
