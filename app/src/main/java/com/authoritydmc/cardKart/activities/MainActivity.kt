package com.authoritydmc.cardKart.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.authoritydmc.cardKart.databinding.ActivityMainBinding
import com.authoritydmc.cardKart.utility.CardView

class MainActivity : AppCompatActivity() {
    private val TAG: String?="RAJ"
    private  lateinit var binding:ActivityMainBinding
    private lateinit var cardView:CardView
    private lateinit var cardView2:CardView
    private lateinit var updateTxtView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        cardView=binding.cardView
        cardView2=binding.cardView2

    }

    override fun onResume() {
        super.onResume()

    }






}