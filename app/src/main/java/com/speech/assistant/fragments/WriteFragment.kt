package com.speech.assistant.fragments

import android.view.LayoutInflater
import com.speech.assistant.BaseFragment
import com.speech.assistant.databinding.FragmentWriteBinding

open class WriteFragment : BaseFragment<FragmentWriteBinding>() {
    override fun viewBindingInflater(inflater: LayoutInflater): FragmentWriteBinding {
        return FragmentWriteBinding.inflate(inflater)
    }

}