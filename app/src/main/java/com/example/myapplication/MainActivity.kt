package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.data.Contact
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewpaperadapter.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    // 뷰 바인딩
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // FragmentStateAdapter 생성
        val viewpagerFragmentAdapter = ViewPagerFragmentAdapter(this)

        // ViewPager2의 adapter 설정
        mainBinding.mainViewPager.adapter = viewpagerFragmentAdapter
        val contactDetailFragment = viewpagerFragmentAdapter.getDetailFragment()
        contactDetailFragment.dataUpdateListener = object: DataUpdateListener{
            override fun onDataUpdated(Contact: Contact) {
                Log.d("test","$Contact")
                val contactListFragment = viewpagerFragmentAdapter.getListFragment()
                if(contactListFragment.statusCheck == false){
                    contactListFragment.listAdapter.addcontact(Contact)
                }else if(contactListFragment.statusCheck == true){
                    contactListFragment.listAdapterGrid.addcontact(Contact)
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

}