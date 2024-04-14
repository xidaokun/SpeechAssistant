package com.speech.assistant

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.gson.Gson
import com.speech.assistant.base.BaseActivity
import com.speech.assistant.databinding.ActivityLoginBinding
import com.speech.assistant.net.RetrofitClient
import com.speech.assistant.utls.NetUtils
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            binding?.loginBtn -> login(binding?.loginAccount?.text.toString(), binding?.loginPwd?.text.toString())
            binding?.registerHere -> register()
            binding?.github -> openGithub()
            binding?.wechat -> openWechat()
            binding?.mobile -> openMobile()
        }
    }

    private fun login(username:String, password:String) {
        val params = mapOf("username" to username, "password" to password)
        val call = RetrofitClient.apiService.login(NetUtils.createJsonBody(params))
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    val string = body?.string()
                    Log.d("TAG", "onResponse: $string")
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
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