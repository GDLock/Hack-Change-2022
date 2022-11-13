package com.otkritie.hackaton.screens.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.otkritie.hackaton.MainActivity
import com.otkritie.hackaton.R
import com.otkritie.hackaton.databinding.FragmentChatScreenBinding
import de.hdodenhof.circleimageview.CircleImageView

class ChatScreen : Fragment() {

    private var _binding: FragmentChatScreenBinding? = null
    private val mBinding = _binding!!

    val args: ChatScreenArgs by navArgs()

    private val adapter = ChatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChatScreenBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.apply {
            rvChat.adapter = adapter
            btnSend.setOnClickListener {
            }

            btnBack.setOnClickListener {
                openMenu()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun openMenu() {
        (requireActivity() as MainActivity).navController.navigate(R.id.action_chatScreen_to_chatMenuScreen)
    }

}
