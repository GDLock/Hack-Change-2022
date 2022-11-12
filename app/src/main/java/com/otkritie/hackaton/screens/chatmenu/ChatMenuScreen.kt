package com.otkritie.hackaton.screens.chatmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.otkritie.hackaton.databinding.FragmentChatMenuScreenBinding

class ChatMenuScreen : Fragment() {

    private val viewModel by viewModels<ChatMenuViewModel>()

    private var _binding: FragmentChatMenuScreenBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatMenuScreenBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
