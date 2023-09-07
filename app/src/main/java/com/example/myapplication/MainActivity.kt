package com.example.myapplication

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.data.Contact
import com.example.myapplication.data.ContactManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewpaperadapter.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    interface onBackPressedLisener {
        fun onbackPressed()
    }

    // 뷰 바인딩
    private lateinit var mainBinding: ActivityMainBinding
    val viewpagerFragmentAdapter by lazy {
        ViewPagerFragmentAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // FragmentStateAdapter 생성
        viewpagerFragmentAdapter
        val contactDetailFragment = viewpagerFragmentAdapter.getDetailFragment()
        Log.d("fragment", "${viewpagerFragmentAdapter.getDetailFragment()}")
        contactDetailFragment.dataUpdateListener = object : DataUpdateListener {
            override fun onDataUpdated(contact: Contact) {
                Log.d("losttest", "$contact")
                val contactListFragment = viewpagerFragmentAdapter.getListFragment()
                if (contactListFragment.statusCheck == false) {
                    contactListFragment.listAdapter.addcontact(contact)
                } else if (contactListFragment.statusCheck == true) {
                    contactListFragment.listAdapterGrid.addcontact(contact)
                }
            }

            override fun updateContact(contact: Contact, position: Int) {
                Log.d("test3", "$contact")
                val contactListFragment = viewpagerFragmentAdapter.getListFragment()
                if (contactListFragment.statusCheck == false) {
                    contactListFragment.listAdapter.adpterEditcontact(contact, position)
                } else if (contactListFragment.statusCheck == true) {
                    contactListFragment.listAdapterGrid.adpterEditcontact(contact, position)
                }
            }
        }
        // ViewPager2의 adapter 설정
        mainBinding.mainViewPager.adapter = viewpagerFragmentAdapter
        val contactListFragment = viewpagerFragmentAdapter.getListFragment().listAdapter
        Log.d("fragment", "${viewpagerFragmentAdapter.getDetailFragment()}")
        contactListFragment.dataUpdateListener = object : DataUpdateListener {
            override fun onDataUpdated(contact: Contact) {
            }

            override fun updateContact(contact: Contact, position: Int) {
                Log.d("test3", "$contact")
                val contactListFragment = viewpagerFragmentAdapter.getListFragment()
                if (contactListFragment.statusCheck == false) {
                    contactListFragment.listAdapter.adpterEditcontact(contact, position)
                } else if (contactListFragment.statusCheck == true) {
                    contactListFragment.listAdapterGrid.adpterEditcontact(contact, position)
                }
            }
        }
        // TabLayout과 ViewPaper2를 연결
        // 1. 탭메뉴의 이름을 리스트로 생성해둔다.
        val tabTitles = listOf<String>("Contact", "MyPage")

        // 2. TabLayout과 ViewPaper2를 연결하고, TabItem의 메뉴명을 설정한다.
        TabLayoutMediator(
            mainBinding.mainTabLayout,
            mainBinding.mainViewPager,
            { tab, position -> tab.text = tabTitles[position] }).attach()
    }

    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is onBackPressedLisener) {
                (fragment as onBackPressedLisener).onbackPressed()
                return
            }

            super.onBackPressed()
        }



    }

}
