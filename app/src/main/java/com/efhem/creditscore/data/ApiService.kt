package com.efhem.creditscore.data

import com.efhem.creditscore.data.models.CreditRemoteResponse
import retrofit2.http.GET


interface ApiService {

    @GET("endpoint.json")
    suspend fun getData(): CreditRemoteResponse
}