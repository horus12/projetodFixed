package com.prd.myprojectD.data

import com.google.gson.annotations.SerializedName

data class RegisterParameters (@SerializedName("userName") val userName: String,
                               @SerializedName("senha") val senha: String,
                               @SerializedName("cpf") val cpf: String,
                               @SerializedName("rg") val rg: String,
                               @SerializedName("contact") val contact: String)