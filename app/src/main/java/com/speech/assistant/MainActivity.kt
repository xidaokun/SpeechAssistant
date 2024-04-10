package com.speech.assistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.speech.assistant.base.BaseActivity
import com.speech.assistant.databinding.ActivityMainBinding
import com.speech.assistant.ui.theme.SpeechAssistantTheme

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun viewBindingInflater(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initView(root: View?) {
        super.initView(root)

    }
}