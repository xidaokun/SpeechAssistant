package com.speech.assistant.fragments

import android.view.LayoutInflater
import com.speech.assistant.base.base.BaseFragment
import com.speech.assistant.base.NoneCtl
import com.speech.assistant.databinding.FragmentSayBinding

open class SayFragment : BaseFragment<FragmentSayBinding, NoneCtl>() {
    override fun viewBindingInflater(inflater: LayoutInflater): FragmentSayBinding {
        return FragmentSayBinding.inflate(inflater)
    }

}