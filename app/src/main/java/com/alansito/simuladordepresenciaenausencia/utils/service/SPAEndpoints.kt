package com.alansito.simuladordepresenciaenausencia.utils.service

import retrofit2.Call
import retrofit2.http.GET


interface SPAEndpoints {

    @GET("/room_light")
    fun turnLight(): Call<String>

    @GET("/room_speaker")
    fun turnRadio(): Call<String>

    @GET("/profile_1")
    fun turnProfile(): Call<String>
}