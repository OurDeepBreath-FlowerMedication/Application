package com.example.flowermedication.device_init

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.flowermedication.R
import androidx.activity.result.contract.ActivityResultContracts
import com.example.flowermedication.get_data.existID
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

//import kotlinx.android.synthetic.main.activity_device_registration.*

class DeviceRegistration : AppCompatActivity() {

    // QR 스캐너 설정
    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult -> // result : 스캔된 결과
        // 등록되지 않는 아이디
        if (!existID(result.contents)) {
            Toast.makeText(this, "${result.contents}는 등록되지 않은 장치입니다.", Toast.LENGTH_LONG).show()
        } else { // 등록된 아이디
            Toast.makeText(
                this,
                "${result.contents}가 등록되었습니다.",
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this, DeviceWifi::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_registration)

        var btnRegisterByQR : Button = findViewById(R.id.btnRegisterByQR)
        var btnRegisterBySerial : Button = findViewById(R.id.btnRegisterBySerial)

        btnRegisterByQR.setOnClickListener {
            barcodeLauncher.launch(ScanOptions())//QR 인식 시작
        }

        btnRegisterBySerial.setOnClickListener {
            startActivity(Intent(this, SerialNumberRegistration::class.java))
        }
    }

}
