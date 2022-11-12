package com.otkritie.hackaton.data.remote

import com.otkritie.hackaton.data.remote.model.auth.AuthorizationRequest
import com.otkritie.hackaton.data.remote.model.auth.AuthorizationResponse
import com.otkritie.hackaton.data.remote.model.dialog.GetDialogResponse
import com.otkritie.hackaton.data.remote.model.user_info.UserInfoRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatApi {

    @POST(API_AUTH)
    suspend fun authorization(
        @Body request: AuthorizationRequest
    ): Response<AuthorizationResponse>

    @POST(API_USER_INFO)
    suspend fun changeUserInfo(
        @Body request: UserInfoRequest
    ): Response<Nothing>

    @GET(API_DIALOG)
    suspend fun getDialogId(): Response<GetDialogResponse>

    companion object {
        const val BASE_URL = "https://hack.invest-open.ru"
        const val WEBSOCKET_URL = "wss://hack.invest-open.ru/chat"

        const val API_AUTH = "auth"
        const val API_USER_INFO = "user/info"
        const val API_DIALOG = "chat/dialog"
    }
}
