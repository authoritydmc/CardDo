package com.authoritydmc.carddo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.authoritydmc.carddo.api.retrofitClient
import com.authoritydmc.carddo.databinding.ActivityMainBinding
import com.authoritydmc.carddo.models.UpdatePOKO
import com.authoritydmc.carddo.utility.CardView
import com.authoritydmc.carddo.utility.UTILS
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityMainBinding
    private lateinit var cardView:CardView
    private lateinit var cardView2:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        cardView=binding.cardView
        cardView2=binding.cardView2
    }

    override fun onStart() {
        super.onStart()

        checkforupdate()
    }

    private fun checkforupdate() {
      
    }
}