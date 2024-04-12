package com.speech.assistant

import android.view.LayoutInflater
import com.speech.assistant.base.BaseActivity
import com.speech.assistant.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override fun viewBindingInflater(inflater: LayoutInflater): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(inflater)
    }



}