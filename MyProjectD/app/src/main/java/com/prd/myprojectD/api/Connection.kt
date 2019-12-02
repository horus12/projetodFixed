package com.prd.myprojectD.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Connection {

    fun createConection(): Api? {

        val service = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
        return service
    }
}