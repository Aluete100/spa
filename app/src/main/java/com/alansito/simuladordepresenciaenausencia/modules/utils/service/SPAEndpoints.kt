package com.alansito.simuladordepresenciaenausencia.modules.utils.service

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface SPAEndpoints {

    @GET("/room_light")
    fun turnLight(): Call<String>

    @GET("/room_speaker")
    fun turnRadio(): Call<String>

    @GET("/profile_1")
    fun turnProfile(): Call<String>
}