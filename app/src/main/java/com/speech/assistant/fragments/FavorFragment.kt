package com.speech.assistant.fragments

import android.view.LayoutInflater
import com.speech.assistant.base.base.BaseFragment
import com.speech.assistant.base.NoneCtl
import com.speech.assistant.databinding.FragmentFavorBinding

open class FavorFragment : BaseFragment<FragmentFavorBinding, NoneCtl>() {
    override fun viewBindingInflater(inflater: LayoutInflater): FragmentFavorBinding {
        return FragmentFavorBinding.inflate(inflater)
    }

}