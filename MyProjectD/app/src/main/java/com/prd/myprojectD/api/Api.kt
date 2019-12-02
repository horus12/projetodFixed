package com.prd.myprojectD.api

import com.prd.myprojectD.data.Login
import com.prd.myprojectD.data.LoginParameters
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @POST("user/Login")
    fun login(@Body body : LoginParameters): Call<Login>
}
