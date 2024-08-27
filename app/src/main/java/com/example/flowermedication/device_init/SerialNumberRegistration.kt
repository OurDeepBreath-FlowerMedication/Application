package com.example.flowermedication.device_init

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flowermedication.R
import com.example.flowermedication.get_data.existID

class SerialNumberRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serial_number_registration)

        var btnRegister : Button = findViewById(R.id.btnRegister)
        var editSerialNumber : EditText = findViewById(R.id.editSerialNumber)
        btnRegister.setOnClickListener {
            val serialNumber = editSerialNumber.text.toString()
            if (!existID(serialNumber)) {
                Toast.makeText(
                    this,
                    "${serialNumber}는 등록되지 않은 장치입니다.",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                Toast.makeText(
                    this,
                    "${serialNumber}가 등록되었습니다.",
                    Toast.LENGTH_LONG
                ).show()
                startActivity(Intent(this, DeviceWifi::class.java))
            }
        }
    }
}
