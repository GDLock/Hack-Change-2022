package com.otkritie.hackaton.di

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.otkritie.hackaton.data.local.preference.AuthPreference
import com.otkritie.hackaton.data.local.preference.AuthPreferenceImpl
import com.otkritie.hackaton.data.manager.AuthManager
import com.otkritie.hackaton.data.remote.ChatApi
import com.otkritie.hackaton.data.remote.interceptor.AuthInterceptor
import com.otkritie.hackaton.data.remote.serializer.RoleAsStringSerializer
import com.otkritie.hackaton.data.remote.websocket.WebSocketClient
import com.otkritie.hackaton.data.remote.websocket.WebSocketListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    val json = Json {
//        ignoreUnknownKeys = true
//        isLenient = true
//        serializersModule = SerializersModule {
//            contextual(RoleAsStringSerializer)
//        }
//    }

    private const val AUTH_PREFERENCE = "auth_preference_key"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        serializersModule = SerializersModule {
            contextual(RoleAsStringSerializer)
        }
    }

    @Provides
    @Singleton
    fun provideStockApi(okHttpClient: OkHttpClient): ChatApi {
        return Retrofit.Builder()
            .baseUrl(ChatApi.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
            .create(ChatApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authManager: AuthManager) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authManager))
            .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
            .build()
    }

    @Provides
    @Singleton
    fun provideWebSocketClient(okHttpClient: OkHttpClient) : WebSocketClient {
        return WebSocketClient(okHttpClient, ChatApi.WEBSOCKET_URL, WebSocketListener())
    }

    @Provides
    @Singleton
    fun provideAuthPreference(@ApplicationContext context: Context): AuthPreference {
        val sharedPreference = context.getSharedPreferences(AUTH_PREFERENCE, MODE_PRIVATE)
        return AuthPreferenceImpl(sharedPreference)
    }
}