package com.otkritie.hackaton.domain.mapper

import com.otkritie.hackaton.data.remote.model.messages.MessageData
import com.otkritie.hackaton.domain.model.MessageViewRenderer

fun MessageData.toViewRenderer(myId: Int) = MessageViewRenderer(
    id = id,
    text = text,
    time = timestamp.toString(),
    mediaUrl = mediaUrl,
    isMine = myId == sender
)
