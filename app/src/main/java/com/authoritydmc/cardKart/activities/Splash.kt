package com.authoritydmc.cardKart.activities

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.authoritydmc.cardKart.R

import com.authoritydmc.cardKart.api.retrofitClient
import com.authoritydmc.cardKart.models.UpdatePOKO
import com.authoritydmc.cardKart.utility.UTILS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.Executor

class Splash : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    companion object{
        lateinit var CURRENT_VERSION:String;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val json= UTILS.parseJSON(this);

        CURRENT_VERSION=json.version;
        checkforauth()
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


        checkforauth()

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                val intent:Intent=Intent(this,MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                startActivity(intent)
                finish()
            },1000 )
        }

    private fun checkforauth() {
        executor=ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
//        val biometricLoginButton =
//            findViewById<Button>(R.id.cardFront)
//        biometricLoginButton.setOnClickListener {
//            biometricPrompt.authenticate(promptInfo)
//        }


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