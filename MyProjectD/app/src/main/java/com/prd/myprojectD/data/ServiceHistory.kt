package com.prd.myprojectD.data

import com.google.gson.annotations.SerializedName

data class ServiceHistory(
    @SerializedName("providerName") val providerName: String,
    @SerializedName("providerCpf") val providerCpf: String,
    @SerializedName("serviceName") val serviceName: String,
    @SerializedName("serviceDescription") val serviceDescription: String,
    @SerializedName("category") val category: String,
    @SerializedName("value") val value: Float
)
