package com.example.flowermedication.screen.menu4

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.flowermedication.MainActivity
import com.example.flowermedication.R
import com.example.flowermedication.device_init.DeviceRegistration
import com.example.flowermedication.device_init.DeviceWifi

class Menu4Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_menu4, container, false)
        var btn_connect_internet : Button = view.findViewById(R.id.btn_connect_internet)
        var btn_cancel_registration : Button = view.findViewById(R.id.btn_cancel_registration)
        var btn_reset_device : Button = view.findViewById(R.id.btn_reset_device)
        var btn_change_device : Button = view.findViewById(R.id.btn_change_device)

        // 기기 인터넷 접속
        btn_connect_internet.setOnClickListener{
            startActivity(Intent(requireContext(), DeviceWifi::class.java))
        }

        // 기기 등록 취소
        btn_cancel_registration.setOnClickListener{
            Toast.makeText(requireContext(), "기기 연결이 해제 되었습니다.", Toast.LENGTH_SHORT).show()
            //서버에서 해당 폰에 대한 아이디 제거
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        btn_reset_device.setOnClickListener{
            Toast.makeText(requireContext(), "연결된 기기에 대한 정보가 모두 제거되었습니다.", Toast.LENGTH_SHORT).show()
            // 서버에서 모든 데이터 지우도록 설정
        }

        // 기기 변경
        btn_change_device.setOnClickListener{
            startActivity(Intent(requireContext(), DeviceRegistration::class.java))
        }

        return view
    }

}