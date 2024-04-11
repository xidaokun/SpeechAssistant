package com.speech.assistant.fragments

import android.view.LayoutInflater
import com.speech.assistant.BaseFragment
import com.speech.assistant.databinding.FragmentMeBinding

open class MeFragment : BaseFragment<FragmentMeBinding>() {
    override fun viewBindingInflater(inflater: LayoutInflater): FragmentMeBinding {
        return FragmentMeBinding.inflate(inflater)
    }

}