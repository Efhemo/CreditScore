package com.efhem.creditscore.data

import com.efhem.creditscore.data.model.CreditRemoteResponse
import retrofit2.http.GET


interface ApiService {

    @GET("endpoint.json")
    suspend fun getData(): CreditRemoteResponse
}