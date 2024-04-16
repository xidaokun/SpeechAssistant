package com.speech.assistant

import android.view.LayoutInflater
import com.speech.assistant.base.base.BaseActivity
import com.speech.assistant.ctls.DetailCtrl
import com.speech.assistant.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailCtrl>() {

    override fun viewBindingInflater(inflater: LayoutInflater): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(inflater)
    }

    override fun ctl(): DetailCtrl {
        return DetailCtrl()
    }
}