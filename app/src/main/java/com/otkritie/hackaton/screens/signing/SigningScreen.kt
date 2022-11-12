package com.otkritie.hackaton.screens.signing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otkritie.hackaton.MainActivity
import com.otkritie.hackaton.R
import com.otkritie.hackaton.databinding.FragmentSigningScreenBinding

class SigningScreen : Fragment() {

    private var _binding: FragmentSigningScreenBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigningScreenBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnStart.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.action_signingScreen_to_chatMenuScreen)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}