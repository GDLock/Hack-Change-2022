package com.otkritie.hackaton

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.otkritie.hackaton.data.remote.ChatApi
import com.otkritie.hackaton.data.remote.model.auth.AuthorizationRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.internal.commonToUtf8String
import retrofit2.HttpException
import java.security.MessageDigest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var chatApi: ChatApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val digest = MessageDigest.getInstance("SHA-256")
//        println(digest.digest("turkey_777".toByteArray()).commonToUtf8String())
//        println(digest.digest("uganda_777".toByteArray()).commonToUtf8String())
        digest.update("uganda_777".toByteArray())
        val byteData = digest.digest()
        val stringBuilder = StringBuilder()
        for (x in byteData) {
            val str = Integer.toHexString(java.lang.Byte.toUnsignedInt(x))
            if (str.length < 2) {
                stringBuilder.append('0')
            }
            stringBuilder.append(str)
        }
        val password = stringBuilder.toString()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                withContext(Dispatchers.IO) {
                    try {
                        Log.e("RESPONSE", chatApi.authorization(AuthorizationRequest("uganda", password)).toString())
                    } catch (e: HttpException) {
                        runOnUiThread { findViewById<TextView>(R.id.title).text = "asdasdasd" }
                    }

                }
            }
        }
    }
}
