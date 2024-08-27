package com.example.flowermedication.device_init

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.flowermedication.R
import com.example.flowermedication.get_data.wifiLink
import com.example.flowermedication.screen.MainScreen
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class DeviceWifi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_wifi)

        val ssid:EditText = findViewById(R.id.ssid_text)
        val pw:EditText = findViewById(R.id.pw_text)
        val btnQRCreate:Button = findViewById(R.id.btn_qr_create)
        val qrImage:ImageView = findViewById(R.id.qr_image)

        btnQRCreate.setOnClickListener{
            if(ssid.text.toString() == ""){
                Toast.makeText(this, "장치에 연결할 SSID를 입력하세요", Toast.LENGTH_LONG).show()
            }else if(pw.text.toString().length < 8){
                Toast.makeText(this, "password를 8자리 이상 입력하세요", Toast.LENGTH_LONG).show()
            }else{
                try {
                    val barcodeEncoder = BarcodeEncoder()
                    val bitmap = barcodeEncoder.encodeBitmap("S:${ssid.text}P:${pw.text}", BarcodeFormat.QR_CODE, 400, 400)
                    qrImage.setImageBitmap(bitmap)
                    if(wifiLink()){
                        startActivity(Intent(this, MainScreen::class.java))
                    }else{
                        Toast.makeText(this, "장치가 해당 Wi-Fi에 연결하는데 실패하였습니다.", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Log.d("QRCreate", "${e.message}")
                }
            }
        }
    }
}