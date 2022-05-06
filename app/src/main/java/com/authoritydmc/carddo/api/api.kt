package com.authoritydmc.carddo.api

import com.authoritydmc.carddo.models.SAMPLEPOKO
import com.authoritydmc.carddo.models.UpdatePOKO
import retrofit2.Call
import retrofit2.http.GET

interface api {
@GET("CardDo/app/src/main/assets/config.json")
fun checkUpdate():Call<UpdatePOKO>

@GET("posts/1")
fun SampleAPI():Call<SAMPLEPOKO>
}