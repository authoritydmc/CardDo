package com.authoritydmc.carddo.utility

import android.content.Context
import android.util.Log
import com.authoritydmc.carddo.models.UpdatePOKO
import com.google.gson.Gson
import java.io.InputStream


class UTILS {


    companion object {
        private val TAG: String?="RAJ"
        final val UPDATE_CHECK_URL: String = "https://authoritydmc.github.io/";
        val PLACEHOLDERAPI = "https://jsonplaceholder.typicode.com/"


        private fun readJSONFromAsset(context: Context): String? {
            var json: String? = null
            try {
                val inputStream: InputStream = context.assets.open("config.json")
                json = inputStream.bufferedReader().use { it.readText() }
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }
            return json
        }

        fun parseJSON(context: Context): UpdatePOKO {
            return Gson().fromJson(readJSONFromAsset(context), UpdatePOKO::class.java)
        }


        fun versionComparer(currentVersion: String, version: String,): Boolean {

            var shouldUpdate = false
            if (currentVersion.equals(version)) return false
            var cur_arr = currentVersion.split(".")
            var ver_arr = version.split(".")

            for (i in 0..2) {
                Log.d(TAG, "versionComparer: ${cur_arr[i]} ${ver_arr[i]}")
                if (ver_arr[i] >= cur_arr[i]) {
                    shouldUpdate = true
                } else {
                    shouldUpdate = false
                    break
                }

            }
            return shouldUpdate

        }

    }
}