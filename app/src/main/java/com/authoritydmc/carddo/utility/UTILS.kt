package com.authoritydmc.carddo.utility

import android.content.res.loader.AssetsProvider
import android.system.Os.open
import com.authoritydmc.carddo.models.UpdatePOKO
import com.google.gson.Gson
import java.io.InputStream
import java.io.IOException
import java.nio.channels.DatagramChannel.open
import java.nio.charset.Charset
import kotlin.coroutines.coroutineContext

class UTILS {
    companion object
    {
      final  val UPDATE_CHECK_URL:String="https://authoritydmc.github.io/";
        val PLACEHOLDERAPI="https://jsonplaceholder.typicode.com/"
        fun readJSONFromAsset(): String? {
            var json: String? = null
            try {
              //  val  inputStream: InputStream = AssetsProvider
                //json = inputStream.bufferedReader().use{it.readText()}
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }
            return json
        }

        fun parseJSON(): UpdatePOKO {
            return Gson().fromJson(readJSONFromAsset(), UpdatePOKO::class.java)
        }
    }

}