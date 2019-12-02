package com.prd.myprojectD.data
import com.google.gson.annotations.SerializedName

data class LoginParameters (@SerializedName("userName") val userName: String,
                            @SerializedName("senha") val senha: String)
