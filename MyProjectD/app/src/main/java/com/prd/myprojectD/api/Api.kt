package com.prd.myprojectD.api

import com.prd.myprojectD.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @POST("Login")
    fun login(@Body body : LoginParameters): Call<Login>

    @POST("user")
    fun register(@Body body : RegisterParameters): Call<Void>

    @GET("service")
    fun getAllServices(): Call<List<ServiceResponses>>

    @POST("service/{userCpf}")
    fun askService(@Path("userCpf") cpf: String,
                   @Body body:askServiceParameters): Call<Void>
}
