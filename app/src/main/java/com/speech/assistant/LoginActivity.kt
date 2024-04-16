package com.speech.assistant

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.speech.assistant.base.DataChangedListener
import com.speech.assistant.base.base.BaseActivity
import com.speech.assistant.ctls.LoginCtl
import com.speech.assistant.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginCtl>(), View.OnClickListener {

    override fun initData() {
        super.initData()
        refreshToken()
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

    override fun onClick(v: View?) {
        when (v) {
            binding?.forgetPassword -> forgetPassword()
            binding?.loginBtn -> login(binding?.loginAccount?.text.toString(), binding?.loginPwd?.text.toString())
            binding?.registerHere -> register()
            binding?.github -> openGithub()
            binding?.wechat -> openWechat()
            binding?.mobile -> openMobile()
        }
    }

    private fun refreshToken() {
        ctl?.refreshToken(object : DataChangedListener<LoginCtl.LoginData> {
            override fun onChanged(data: LoginCtl.LoginData) {
                if (data.status == 1) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onBefore() {
                showWaiting()
            }

            override fun onAfter(message: String?) {
                dismissWaiting()
                Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun login(username:String, password:String) {
        ctl?.login(username, password, object : DataChangedListener<LoginCtl.LoginData> {
            override fun onChanged(data: LoginCtl.LoginData) {
                if (data.status == 1) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onBefore() {
                showWaiting()
            }

            override fun onAfter(message: String?) {
                dismissWaiting()
                Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showWaiting() {
        findViewById<View>(R.id.mask_layout).visibility = View.VISIBLE
        AnimationUtils.loadAnimation(this, R.anim.rotate)
        findViewById<ImageView>(R.id.waiting_process).startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate))
    }

    private fun dismissWaiting() {
        findViewById<View>(R.id.mask_layout).visibility = View.GONE
        findViewById<ImageView>(R.id.waiting_process).clearAnimation()
    }

    private fun register() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
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

    override fun viewBindingInflater(inflater: LayoutInflater): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(inflater)
    }

    override fun ctl(): LoginCtl {
        return LoginCtl()
    }

}