package com.example.myroomapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myroomapp.databinding.ActivityMainBinding
import com.example.myroomapp.databinding.ActivityMainBinding.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var context: Context
        val scope by lazy { CoroutineScope(SupervisorJob()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        context = applicationContext
        super.onCreate(savedInstanceState)
        with(ActivityMainBinding.inflate(layoutInflater)) {
            var viewModel = AppModelView()
            setContentView(root)
        }

    }
}