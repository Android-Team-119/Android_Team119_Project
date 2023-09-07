package com.example.myapplication.viewpaperadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ContactDetailFragment
import com.example.myapplication.ContactListFragment

class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    // 1. ViewPager2에 연결할 Fragment 들을 생성

    val fragmentList = listOf<Fragment>(ContactListFragment(), ContactDetailFragment())


    // 2. ViesPager2에서 노출시킬 Fragment 의 갯수 설정
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    // 3. ViewPager2의 각 페이지에서 노출할 Fragment 설정
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
    fun getDetailFragment():ContactDetailFragment{
        return fragmentList[1] as ContactDetailFragment
    }
    fun getListFragment():ContactListFragment{
        return fragmentList[0] as ContactListFragment
    }
}