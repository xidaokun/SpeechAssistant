package com.speech.assistant

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.speech.assistant.base.BaseActivity
import com.speech.assistant.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(), View.OnClickListener {
    override fun viewBindingInflater(inflater: LayoutInflater): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(inflater)
    }
    override fun initListener(root: View?) {
        super.initListener(root)
        binding?.forgetPassword?.setOnClickListener(this)
        binding?.loginBtn?.setOnClickListener(this)
        binding?.registerHere?.setOnClickListener(this)
        binding?.github?.setOnClickListener(this)
        binding?.wechat?.setOnClickListener(this)
        binding?.mobile?.setOnClickListener(this)
    }

    override fun initData() {
        super.initData()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding?.forgetPassword -> forgetPassword()
            binding?.loginBtn -> login()
            binding?.registerHere -> register()
            binding?.github -> openGithub()
            binding?.wechat -> openWechat()
            binding?.mobile -> openMobile()
        }
    }

    private fun login() {
        // Login logic
//        val intent = Intent(this, MainActivity::class.java)
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun register() {
        // Register logic
    }

    private fun forgetPassword() {
        // Forget password logic
    }

    private fun openGithub() {
        // Open Github logic
    }

    private fun openWechat() {
        // Open Wechat logic
    }

    private fun openMobile() {
        // Open Mobile logic
    }

}