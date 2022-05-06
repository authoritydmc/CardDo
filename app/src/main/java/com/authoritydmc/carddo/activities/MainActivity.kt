package com.authoritydmc.carddo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
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
    private  lateinit var binding:ActivityMainBinding
    private lateinit var cardView:CardView
    private lateinit var cardView2:CardView

    companion object{
        var CURRENT_VERSION="";
    }
    init {

    }
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

checkforUpdate()
    }

    private fun checkforUpdate() {
        retrofitClient.instance.checkUpdate().enqueue(object : Callback<UpdatePOKO?> {
            override fun onResponse(call: Call<UpdatePOKO?>, response: Response<UpdatePOKO?>) =
                Toast.makeText(applicationContext,response.body()?.downloadURL,Toast.LENGTH_LONG).show()



            override fun onFailure(call: Call<UpdatePOKO?>, t: Throwable) {
                Toast.makeText(applicationContext,"Failed to get JSON",Toast.LENGTH_LONG
                ).show()
                Log.d("RAJ", "onResponse: found response ${t.message}")

            }
        })
    }


}