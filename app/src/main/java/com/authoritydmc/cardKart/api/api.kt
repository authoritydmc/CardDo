package com.authoritydmc.cardKart.api

import com.authoritydmc.cardKart.models.SAMPLEPOKO
import com.authoritydmc.cardKart.models.UpdatePOKO
import retrofit2.Call
import retrofit2.http.GET

interface api {
@GET("CardKart/app/src/main/assets/config.json")
fun checkUpdate():Call<UpdatePOKO>

@GET("posts/1")
fun SampleAPI():Call<SAMPLEPOKO>
}