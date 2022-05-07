package com.authoritydmc.cardKart.utility

import android.content.Context
import android.util.Log
import com.authoritydmc.cardKart.models.UpdatePOKO
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

            Log.d(TAG, "versionComparer: Current Version : $currentVersion got Version=$version")
            var shouldUpdate = false
            if (currentVersion.equals(version)) return false
            var cur_arr = currentVersion.split(".")
            var ver_arr = version.split(".")

            for (i in 0..2) {
                if ((ver_arr[i].toInt()) > cur_arr[i].toInt()) {
                    Log.d(TAG, "versionComparer: version :${ver_arr[i]} current: ${cur_arr[i]} greater")

                    shouldUpdate = true
                    break
                } else if (ver_arr[i].toInt()==cur_arr[i].toInt()) {
                    Log.d(TAG, "versionComparer: version :${ver_arr[i]} current: ${cur_arr[i]} same")

                    shouldUpdate=true
                }
                else
                {
                    Log.d(TAG, "versionComparer: version :${ver_arr[i]} current: ${cur_arr[i]} failed")

                    shouldUpdate = false
                    break
                }

            }
            return shouldUpdate

        }

    }
}