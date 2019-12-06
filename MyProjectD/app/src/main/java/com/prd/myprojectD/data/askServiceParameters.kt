package com.prd.myprojectD.data

import com.google.gson.annotations.SerializedName


data class askServiceParameters (@SerializedName("providerCpf") val providerCpf: String,
                            @SerializedName("serviceName") val serviceName: String)