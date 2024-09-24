package com.example.myservice

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val additionalPadding = resources.getDimensionPixelSize(R.dimen.additional_padding)

            v.setPadding(
                systemBars.left + additionalPadding,
                systemBars.top + additionalPadding,
                systemBars.right + additionalPadding,
                systemBars.bottom + additionalPadding
            )

            insets
        }


        val serviceIntent = Intent(this, MyBackgroundService::class.java)

        // menjalankan service
        binding.btnStartBackgroundService.setOnClickListener {
            startService(serviceIntent)
        }

        // menghentikan service
        binding.btnStopBackgroundService.setOnClickListener {
            stopService(serviceIntent)
        }

        // kelas foreground service

        val foregroundServiceIntent = Intent(this, MyForegroundService::class.java)
        binding.btnStartForegroundService.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(foregroundServiceIntent)
            } else {
                startService(foregroundServiceIntent)
            }
        }
        binding.btnStopForegroundService.setOnClickListener {
            stopService(foregroundServiceIntent)
        }

    }
}