package com.authoritydmc.cardKart.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.authoritydmc.cardKart.R

import com.authoritydmc.cardKart.api.retrofitClient
import com.authoritydmc.cardKart.models.UpdatePOKO
import com.authoritydmc.cardKart.utility.UTILS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Splash : AppCompatActivity() {


    companion object{
        lateinit var CURRENT_VERSION:String;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val json= UTILS.parseJSON(this);

        CURRENT_VERSION=json.version;
        checkforUpdate()


    }

    private fun routetoUpdate() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val intent:Intent=Intent(this,UpdateActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            startActivity(intent)
            finish()
        },1000 )
    }

    private fun routetoMain() {


            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                val intent:Intent=Intent(this,MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                startActivity(intent)
                finish()
            },1000 )
        }


    private fun checkforUpdate() {
        val TAG="RAJ"
        var shouldUpdate :Boolean=false

        try {
            retrofitClient.instance.checkUpdate().enqueue(object : Callback<UpdatePOKO?> {
                override fun onResponse(call: Call<UpdatePOKO?>, response: Response<UpdatePOKO?>) {
try {


       shouldUpdate= UTILS.versionComparer(CURRENT_VERSION, response.body()!!.version)
}catch (e:Exception)
{
    Log.e(TAG, "onResponse: Error ${e.message} Possible due to project Name change/check URL error", )
routetoMain()
}

                    Log.d(TAG, "Should Update: $shouldUpdate")
                    if (shouldUpdate) {

                        Toast.makeText(
                            applicationContext,
                            "New Version found ${response.body()!!.version.toString()}",
                            Toast.LENGTH_SHORT
                        ).show()
                        routetoUpdate()

                    } else {
                        Toast.makeText(applicationContext, "No update found", Toast.LENGTH_SHORT)
                            .show()

                        routetoMain()

                    }
                }


                override fun onFailure(call: Call<UpdatePOKO?>, t: Throwable) {

                    routetoMain()
                }
            })
        }catch (i:Exception)
        {
            routetoMain()
        }
    }
}