package com.otkritie.hackaton.screens.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otkritie.hackaton.data.remote.model.messages.MessageData
import com.otkritie.hackaton.data.remote.websocket.WebSocketListener
import com.otkritie.hackaton.data.repository.ChatRepository
import com.otkritie.hackaton.domain.mapper.toViewRenderer
import com.otkritie.hackaton.domain.model.MessageViewRenderer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("myId")

    private val _list = MutableLiveData<List<MessageViewRenderer>>()
    val list: LiveData<List<MessageViewRenderer>> = _list

//    private val _message = MutableLiveData("")
//    val message: LiveData<String> = _message

    var message = ""

    private val _clearMessageEvent = Channel<String>()
    val clearMessageEvent = _clearMessageEvent.receiveAsFlow()

//    private val _newMessageEvent = Channel<MessageData>()
//    val newMessageEvent = _newMessageEvent.receiveAsFlow()
//
////    fun setMessage(value: String) {
////        _message.value = value
////    }
//
////    val listener = WebSocketListener()

    var newMessage: MessageData? = null

    fun getHistory() {
        viewModelScope.launch {
            id?.let {
                chatRepository.getHistory(id,
                    onSuccess = {
                        _list.value = it?.messages?.map { it.toViewRenderer(100777) }
                    },
                    onFailure = {

                    })
            }

            chatRepository.connect(WebSocketListener {
                viewModelScope.launch {
                    val message = it.toViewRenderer(100777)
                    _list.value = listOf(message) + (list.value ?: emptyList())
                }
            })
        }
    }

    fun sendMessage() {
        viewModelScope.launch {
            id?.let {
                if (message.isNotEmpty()) {
                    chatRepository.sendMessage(
                        id, message,
                        onSuccess = {
                            message = ""
                            _clearMessageEvent.trySend("")
                        },
                        onFailure = {}
                    )
                }
            }
        }
    }

    override fun onCleared() {
        chatRepository.disconnect()
    }
}
