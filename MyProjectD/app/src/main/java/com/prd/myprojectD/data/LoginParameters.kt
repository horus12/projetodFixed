package com.prd.myprojectD.data
import com.google.gson.annotations.SerializedName

data class LoginParameters (@SerializedName("cpf") val userName: String,
                            @SerializedName("senha") val senha: String)
