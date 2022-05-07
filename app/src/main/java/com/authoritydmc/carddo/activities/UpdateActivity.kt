package com.authoritydmc.carddo.activities


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.authoritydmc.carddo.R
import com.authoritydmc.carddo.api.retrofitClient
import com.authoritydmc.carddo.databinding.ActivityMainBinding
import com.authoritydmc.carddo.databinding.ActivityUpdateBinding
import com.authoritydmc.carddo.models.UpdatePOKO
import com.authoritydmc.carddo.utility.UTILS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityUpdateBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding=ActivityUpdateBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        showData()

    }

    private fun showData(){
        val TAG="RAJ"

        Log.d("RAJ", "Current Version: ${Splash.CURRENT_VERSION}")
        retrofitClient.instance.checkUpdate().enqueue(object : Callback<UpdatePOKO?> {
            override fun onResponse(call: Call<UpdatePOKO?>, response: Response<UpdatePOKO?>)
            {

                val shouldUpdate=UTILS.versionComparer(Splash.CURRENT_VERSION, response.body()!!.version)


                Log.d(TAG, "Should Update: $shouldUpdate")
                if (shouldUpdate)
                {

                    Toast.makeText(applicationContext,"Update available ${response.body()!!.version}",Toast.LENGTH_LONG).show()
                    Log.d(TAG, "onResponse: version update availabel from ${response.body()!!.downloadURL}")

                    binding.updateInfoUpdateActivity.setText("Current Version:${Splash.CURRENT_VERSION} \nUpdate version : ${response.body()!!.version} Available from ${response.body()!!.downloadURL} ")
                }else
                {
                    binding.updateInfoUpdateActivity.setText("Current Version : ${Splash.CURRENT_VERSION}")
                    binding.updateInfoUpdateActivity.textSize=14.0f;

                }
            }



            override fun onFailure(call: Call<UpdatePOKO?>, t: Throwable) {
                Toast.makeText(applicationContext,"Failed to check update !! ",Toast.LENGTH_SHORT
                ).show()
                Log.d("RAJ", "onResponse: found response ${t.message}")

            }
        })
    }
}