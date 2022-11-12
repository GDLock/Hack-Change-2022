package com.otkritie.hackaton.data.remote

import com.otkritie.hackaton.data.remote.ChatApi.Companion.API_AUTH
import com.otkritie.hackaton.data.remote.model.auth.AuthorizationRequest
import com.otkritie.hackaton.data.remote.model.auth.AuthorizationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {

    @POST(API_AUTH)
    suspend fun authorization(
        @Body request: AuthorizationRequest
    ): AuthorizationResponse

    companion object {
        const val BASE_URL = "https://hack.invest-open.ru"
        const val WEBSOCKET_URL = "wss://hack.invest-open.ru/chat"

        const val API_AUTH = "auth"
    }
}
