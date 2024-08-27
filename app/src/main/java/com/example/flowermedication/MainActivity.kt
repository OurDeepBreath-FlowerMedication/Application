package com.example.flowermedication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.flowermedication.device_init.DeviceRegistration
import com.example.flowermedication.device_init.DeviceWifi
import com.example.flowermedication.screen.MainScreen

class MainActivity : AppCompatActivity() {

    private var condition: Int = 3 // 조건에 따라 값을 변경

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            when (condition) {
                1 -> startActivity(Intent(this, DeviceRegistration::class.java))
                2 -> startActivity(Intent(this, DeviceWifi::class.java))
                3 -> startActivity(Intent(this, MainScreen::class.java))
            }
            finish()
        }, 3000) // 3초 후에 실행
    }
}
