package com.example.flowermedication.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.FragmentContainerView
import com.example.flowermedication.R

class MainScreen : AppCompatActivity() {
    var cur_screen = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        var menuButton : ImageButton = findViewById(R.id.menuButton)
        var menuFragment: FragmentContainerView = findViewById(R.id.fragment_menu)

        var menu1Fragment: FragmentContainerView = findViewById(R.id.fragment_menu1)
        var menu2Fragment: FragmentContainerView = findViewById(R.id.fragment_menu2)
        var menu3Fragment: FragmentContainerView = findViewById(R.id.fragment_menu3)
        var menu4Fragment: FragmentContainerView = findViewById(R.id.fragment_menu4)


        // 버튼 클릭시에 메뉴 프레그먼트 보여지도록
        menuFragment.visibility = View.GONE
        menuButton.setOnClickListener{
            menuFragment.visibility = View.VISIBLE
        }

        menu1Fragment.visibility = View.GONE
        menu2Fragment.visibility = View.GONE
        menu3Fragment.visibility = View.GONE
        menu4Fragment.visibility = View.GONE

    }

    // 프래그먼트에서, 메뉴 닫기 누르면 닫히도록
    fun goShecdule(){
        var menuFragment: FragmentContainerView = findViewById(R.id.fragment_menu)
        menuFragment.visibility = View.GONE
    }

    fun menuSelect(menu:Int){

        var menu0Fragment: FragmentContainerView = findViewById(R.id.fragment_menu0)
        var menu1Fragment: FragmentContainerView = findViewById(R.id.fragment_menu1)
        var menu2Fragment: FragmentContainerView = findViewById(R.id.fragment_menu2)
        var menu3Fragment: FragmentContainerView = findViewById(R.id.fragment_menu3)
        var menu4Fragment: FragmentContainerView = findViewById(R.id.fragment_menu4)

        when(cur_screen){
            0-> menu0Fragment.visibility = View.GONE
            1-> menu1Fragment.visibility = View.GONE
            2-> menu2Fragment.visibility = View.GONE
            3-> menu3Fragment.visibility = View.GONE
            4-> menu4Fragment.visibility = View.GONE
        }

        cur_screen = menu

        when(menu){
            0-> menu0Fragment.visibility = View.VISIBLE
            1-> menu1Fragment.visibility = View.VISIBLE
            2-> menu2Fragment.visibility = View.VISIBLE
            3-> menu3Fragment.visibility = View.VISIBLE
            4-> menu4Fragment.visibility = View.VISIBLE
        }

        goShecdule()
    }
}