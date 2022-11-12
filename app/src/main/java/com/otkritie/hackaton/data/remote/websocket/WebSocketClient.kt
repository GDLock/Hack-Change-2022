package com.otkritie.hackaton.data.remote.websocket

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class WebSocketClient(
    private val client: OkHttpClient,
    private val socketUrl: String,
    private val listener: WebSocketListener
) {
    private var webSocket: WebSocket? = null
    private var shouldReconnect = false

    private fun initWebSocket() {
        Log.e("socketCheck", "initWebSocket() socketurl = $socketUrl")
        val request = Request.Builder().url(url = socketUrl).build()
        webSocket = client.newWebSocket(request, listener)
        client.dispatcher.executorService.shutdown()
    }

    fun connect() {
        shouldReconnect = true
        initWebSocket()
    }

    fun reconnect() {
        initWebSocket()
    }

    fun disconnect() {
        shouldReconnect = false
        webSocket?.cancel()
        webSocket = null
    }
}
