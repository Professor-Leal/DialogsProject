package com.rafaelleal.android.dialogsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelleal.android.dialogsproject.databinding.ActivityMainBinding

// Chamadas de diálogos
// https://developer.android.com/guide/topics/ui/dialogs?hl=pt-br

// Material Design:
// https://m2.material.io/components/dialogs/android

class MainActivity : AppCompatActivity() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // https://developer.android.com/topic/libraries/view-binding#activities ///////////////////////
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


}