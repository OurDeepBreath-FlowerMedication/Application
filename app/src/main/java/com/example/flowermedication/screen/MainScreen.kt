package com.example.flowermedication.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.flowermedication.R
import com.example.flowermedication.screen.menu0.Menu0Fragment
import com.example.flowermedication.screen.menu1.Menu1Fragment
import com.example.flowermedication.screen.menu2.Menu2Fragment
import com.example.flowermedication.screen.menu3.Menu3Fragment
import com.example.flowermedication.screen.menu4.Menu4Fragment
import java.util.Calendar

class MainScreen : AppCompatActivity() {
    var cur_screen = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        var menuButton : ImageButton = findViewById(R.id.menuButton)
        var menuFragment: FragmentContainerView = findViewById(R.id.fragment_menu)

        // 버튼 클릭시에 메뉴 프레그먼트 보여지도록
        menuFragment.visibility = View.GONE
        menuButton.setOnClickListener{
            menuFragment.visibility = View.VISIBLE
        }

    }

    // 프래그먼트에서, 메뉴 닫기 누르면 닫히도록
    fun goShecdule(){
        var menuFragment: FragmentContainerView = findViewById(R.id.fragment_menu)
        menuFragment.visibility = View.GONE
    }

    fun menuSelect(menu:Int){

        //var Fragment: FragmentContainerView = findViewById(R.id.fragment)
        val fragmentTransaction = supportFragmentManager.beginTransaction()


        when(cur_screen){
            0-> fragmentTransaction.replace(R.id.fragment, Menu0Fragment())
            1-> fragmentTransaction.replace(R.id.fragment, Menu1Fragment())
            2-> fragmentTransaction.replace(R.id.fragment, Menu2Fragment(
                (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 5) % 7))
            3-> fragmentTransaction.replace(R.id.fragment, Menu3Fragment(
                (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 5) % 7))
            4-> fragmentTransaction.replace(R.id.fragment, Menu4Fragment())
        }

        cur_screen = menu

        when(cur_screen){
            0-> fragmentTransaction.replace(R.id.fragment, Menu0Fragment())
            1-> fragmentTransaction.replace(R.id.fragment, Menu1Fragment())
            2-> fragmentTransaction.replace(R.id.fragment, Menu2Fragment(
                (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 5) % 7))
            3-> fragmentTransaction.replace(R.id.fragment, Menu3Fragment(
                (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 5) % 7))
            4-> fragmentTransaction.replace(R.id.fragment, Menu4Fragment())
        }

        fragmentTransaction.commit()

        goShecdule()
    }
}