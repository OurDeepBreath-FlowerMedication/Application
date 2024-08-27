package com.example.flowermedication.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.flowermedication.R

class MenuFragment : Fragment() {
    var Activity: MainScreen?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_menu, container, false)
        val menuBack : ImageButton = view.findViewById(R.id.menuBack)

        menuBack.setOnClickListener{
            Activity?.goShecdule()
        }
        val menu0 : TextView = view.findViewById(R.id.menu0)
        menu0.setOnClickListener{Activity?.menuSelect(0)}

        val menu1 : TextView = view.findViewById(R.id.menu1)
        menu1.setOnClickListener{Activity?.menuSelect(1)}

        val menu2 : TextView = view.findViewById(R.id.menu2)
        menu2.setOnClickListener{Activity?.menuSelect(2)}

        val menu3 : TextView = view.findViewById(R.id.menu3)
        menu3.setOnClickListener{Activity?.menuSelect(3)}

        val menu4 : TextView = view.findViewById(R.id.menu4)
        menu4.setOnClickListener{Activity?.menuSelect(4)}

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Activity = context as MainScreen
    }

}