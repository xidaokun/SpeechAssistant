package com.speech.assistant.fragments

import android.view.LayoutInflater
import com.speech.assistant.BaseFragment
import com.speech.assistant.databinding.FragmentSayBinding

open class SayFragment : BaseFragment<FragmentSayBinding>() {
    override fun viewBindingInflater(inflater: LayoutInflater): FragmentSayBinding {
        return FragmentSayBinding.inflate(inflater)
    }

}