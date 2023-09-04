package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

        // 툴바 연결
        setSupportActionBar(mainBinding.mainToolbar) // 커스텀 한 툴바를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) // 액션바에 나오는 제목의 표시/ false로 해야 아래 타이틀이 나옴
        mainBinding.mainToolbar.title = "도와줘요 119" // 툴바 타이틀

        // FragmentStateAdapter 생성
        val viewpagerFragmentAdapter = ViewPagerFragmentAdapter(this)

        // ViewPager2의 adapter 설정
        mainBinding.mainViewPager.adapter = viewpagerFragmentAdapter


        // TabLayout과 ViewPaper2를 연결
        // 1. 탭메뉴의 이름을 리스트로 생성해둔다.
        val tabTitles = listOf<String>("Contact", "MyPage")

        // 2. TabLayout과 ViewPaper2를 연결하고, TabItem의 메뉴명을 설정한다.
        TabLayoutMediator(mainBinding.mainTabLayout, mainBinding.mainViewPager, {tab, position -> tab.text = tabTitles[position]}).attach()

        // Floating 버튼 클릭 리스너
        mainBinding.mainFloatBtn.setOnClickListener {
            Toast.makeText(this@MainActivity, "Floating Button", Toast.LENGTH_SHORT).show()
        }
        febClickEvent()
    }

    // 툴바 아이템 사용
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_item_toolbarmenu, menu)
        return true
    }

    // 툴바 아이템 클릭 리스너
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list_item -> { // 리스트 형식
                Toast.makeText(this@MainActivity, "List Button", Toast.LENGTH_SHORT).show()
                return super.onOptionsItemSelected(item)
            }

            R.id.grid_item -> { // 그리드 형식
                Toast.makeText(this@MainActivity, "Grid Button", Toast.LENGTH_SHORT).show()
                return super.onOptionsItemSelected(item)
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
    private fun febClickEvent(){
        mainBinding.mainFloatBtn.setOnClickListener{
            val dialog = AddNumberDialog()
            dialog.show(supportFragmentManager,"AddNumberDialog")
        }

    }
}