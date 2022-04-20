package com.authoritydmc.carddo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.authoritydmc.carddo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        binding.textView.setText("THis is custom coding");


    }
}