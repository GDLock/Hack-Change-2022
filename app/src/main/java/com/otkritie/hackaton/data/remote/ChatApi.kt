package com.otkritie.hackaton.data.remote

import com.otkritie.hackaton.data.remote.ChatApi.Companion.API_AUTH
import com.otkritie.hackaton.data.remote.model.auth.AuthorizationRequest
import com.otkritie.hackaton.data.remote.model.auth.AuthorizationResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okio.BufferedSink
import org.json.JSONObject
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


fun createRequestBody(
    login: String,
    password: String,
    mediaType: String = "application/json"
): RequestBody {
    val rb = JSONObject().apply {
        put("login", login)
        put("password", password)
    }
        .toString()
        .toRequestBodyWithoutCharsetInMediaType(
            mediaType.toMediaType()
        )

    return rb
}

private fun String.toRequestBodyWithoutCharsetInMediaType(mt: MediaType): RequestBody {
    val bytes = toByteArray(Charsets.UTF_8)
    return object : RequestBody() {
        override fun contentType() = mt
        override fun contentLength() = bytes.size.toLong()
        override fun writeTo(sink: BufferedSink) {
            sink.write(bytes, 0, bytes.size)
        }
    }
}
