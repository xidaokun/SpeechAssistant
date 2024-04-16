package com.speech.assistant.fragments

import android.view.LayoutInflater
import com.speech.assistant.base.base.BaseFragment
import com.speech.assistant.base.NoneCtl
import com.speech.assistant.ctls.MeCtl
import com.speech.assistant.databinding.FragmentMeBinding

open class MeFragment : BaseFragment<FragmentMeBinding, MeCtl>() {



    override fun viewBindingInflater(inflater: LayoutInflater): FragmentMeBinding {
        return FragmentMeBinding.inflate(inflater)
    }

    override fun ctl(): MeCtl? {
        return MeCtl()
    }

}