package com.speech.assistant

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.speech.assistant.base.BaseActivity
import com.speech.assistant.databinding.ActivityLoginBinding
import com.speech.assistant.datas.SResponse
import com.speech.assistant.net.RetrofitClient
import com.speech.assistant.utls.NetUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity<ActivityLoginBinding>(), View.OnClickListener {

    private val TAG : String = "LoginActivity"

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
            binding?.loginBtn -> login(binding?.loginAccount?.text.toString(), binding?.loginPwd?.text.toString())
            binding?.registerHere -> register()
            binding?.github -> openGithub()
            binding?.wechat -> openWechat()
            binding?.mobile -> openMobile()
        }
    }

    private fun login(username:String, password:String) {
        if(username.isEmpty() or password.isEmpty()) {
            Toast.makeText(this@LoginActivity, "account or password is empty!", Toast.LENGTH_SHORT).show()
            return
        }

        showWaiting()
        val params = mapOf("name" to username, "password" to password)
        val call = RetrofitClient.apiService.login(NetUtils.createJsonBody(params))
        call.enqueue(object : Callback<SResponse<Any>> {
            override fun onResponse(call: Call<SResponse<Any>>, response: Response<SResponse<Any>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d(TAG, "onResponse: ${body?.message}")
                    if(body?.status == 1) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    dismissWaiting()
                    Toast.makeText(this@LoginActivity, body?.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SResponse<Any>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                dismissWaiting()
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
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

}