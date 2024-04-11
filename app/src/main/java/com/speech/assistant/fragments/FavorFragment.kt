package com.speech.assistant.fragments

import android.view.LayoutInflater
import com.speech.assistant.BaseFragment
import com.speech.assistant.databinding.FragmentFavorBinding

open class FavorFragment : BaseFragment<FragmentFavorBinding>() {
    override fun viewBindingInflater(inflater: LayoutInflater): FragmentFavorBinding {
        return FragmentFavorBinding.inflate(inflater)
    }

}