package com.authoritydmc.carddo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    companion object{
        lateinit var CURRENT_VERSION:String;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        cardView=binding.cardView
        cardView2=binding.cardView2
        updateTxtView=binding.txtViewUpdateInfo
    }

    override fun onResume() {
        super.onResume()


        val json= UTILS.parseJSON(this);

        CURRENT_VERSION=json.version;
checkforUpdate()
    }

    private fun checkforUpdate() {
        Log.d("RAJ", "Current Version: $CURRENT_VERSION")
        retrofitClient.instance.checkUpdate().enqueue(object : Callback<UpdatePOKO?> {
            override fun onResponse(call: Call<UpdatePOKO?>, response: Response<UpdatePOKO?>)
            {

                val shouldUpdate=UTILS.versionComparer(CURRENT_VERSION, response.body()!!.version)

                Log.d(TAG, "Should Update: $shouldUpdate")
                if (shouldUpdate)
                {
                    Toast.makeText(applicationContext,"Update available ${response.body()!!.version}",Toast.LENGTH_LONG).show()
                    Log.d(TAG, "onResponse: version update availabel from ${response.body()!!.downloadURL}")
                  updateTxtView.setVisibility(View.VISIBLE)
                    updateTxtView.setText("Update version : ${response.body()!!.version} Available from ${response.body()!!.downloadURL} ")
                }else
                {
                    updateTxtView.visibility=View.GONE
                }
            }



            override fun onFailure(call: Call<UpdatePOKO?>, t: Throwable) {
                Toast.makeText(applicationContext,"Failed to get JSON",Toast.LENGTH_LONG
                ).show()
                Log.d("RAJ", "onResponse: found response ${t.message}")

            }
        })
    }




}