package com.example.myapplication

import android.os.Build
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Contact
import com.example.myapplication.data.ContactManager
import com.example.myapplication.databinding.ContactlistFragmentBinding

class ContactListFragment : Fragment() {// Viewtype 사용

    private lateinit var binding: ContactlistFragmentBinding
//    private val listAdapter by lazy{
//        ContactAdapter()
//    }
    var statusCheck = false

    val listAdapter by lazy{
        ContactAdapter(listType = false)
    }
    val listAdapterGrid by lazy{
        ContactAdapter(listType = true)
    }
    fun change(){
        binding.recyclerView.scrollToPosition(ContactAdapter().contactList.size-1)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ContactlistFragmentBinding.inflate(inflater, container, false)

        binding.run {
            listToolbar.run {
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
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        // 플로팅 버튼 fade
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var floatTop = true

        binding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerView.canScrollVertically(-1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.listFloatBtn.startAnimation(fadeOut)
                    binding.listFloatBtn.visibility = View.GONE
                    floatTop = true
                } else {
                    if(floatTop) {
                        binding.listFloatBtn.visibility = View.VISIBLE
                        binding.listFloatBtn.startAnimation(fadeIn)
                        floatTop = false
                    }
                }
            }
        })

        // Floating 버튼 클릭 리스너
        binding.listFloatBtn.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
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
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))

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

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.RIGHT
    ) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val phoneNum = ContactManager.getPhoneByPosition(position)
            val phoneNumToString = "tel:" + phoneNum.split("-").joinToString("")
            val callIntent = Intent(Intent.ACTION_DIAL,Uri.parse(phoneNumToString))
            startActivity(callIntent)
            listAdapter.notifyItemChanged(viewHolder.adapterPosition)
        }

//        override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
//            //return super.getSwipeEscapeVelocity(defaultValue)
//            return defaultValue * 20
//        }

//        override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
//            return 0.3f
//        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            Log.d("clear", "clearview")
        }

    })



}
