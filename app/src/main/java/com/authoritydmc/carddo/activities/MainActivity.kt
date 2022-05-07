package com.authoritydmc.carddo.activities

import android.graphics.Color
import android.graphics.fonts.Font
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.authoritydmc.carddo.api.retrofitClient
import com.authoritydmc.carddo.databinding.ActivityMainBinding
import com.authoritydmc.carddo.models.UpdatePOKO
import com.authoritydmc.carddo.utility.CardView
import com.authoritydmc.carddo.utility.UTILS
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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