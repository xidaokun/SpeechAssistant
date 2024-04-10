package com.speech.assistant

import android.view.LayoutInflater
import com.speech.assistant.base.BaseActivity
import com.speech.assistant.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun viewBindingInflater(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }
}