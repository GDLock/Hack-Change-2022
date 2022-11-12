package com.otkritie.hackaton.screens.chatmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.otkritie.hackaton.R
import com.otkritie.hackaton.databinding.FragmentChatMenuScreenBinding
import com.otkritie.hackaton.domain.model.Question

class ChatMenuScreen : Fragment() {

    private val lst = listOf<Question>(
        Question(R.drawable.test_avatar, "С чего начать"),
        Question(R.drawable.test_avatar, "Как инвестировать"),
        Question(R.drawable.test_avatar, "Какие акции покупать первыми"),
        Question(R.drawable.test_avatar, "С чего начать"),
        Question(R.drawable.test_avatar, "С чего начать"),
    )

    private val viewModel by viewModels<ChatMenuViewModel>()
    private var questionAdapter = QuestionAdapter()

    private var _binding: FragmentChatMenuScreenBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatMenuScreenBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {

            val action = 



            questionAdapter.setList(lst)
            rvListQuestions.adapter = questionAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        @JvmStatic
        fun newInstance(myId: Int) = ChatMenuScreen().apply {
            arguments = Bundle().apply {
                putInt("chatId", myId)
            }

        }

    }

}
