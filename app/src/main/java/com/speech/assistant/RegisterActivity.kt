package com.speech.assistant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import com.speech.assistant.base.BaseActivity
import com.speech.assistant.databinding.ActivityRegisterBinding
import com.speech.assistant.net.RetrofitClient
import com.speech.assistant.utls.NetUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(), OnClickListener {

    override fun viewBindingInflater(inflater: LayoutInflater): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(inflater)
    }

    override fun initListener(root: View?) {
        super.initListener(root)
        binding?.btnSure?.setOnClickListener(this)
        binding?.btnBack?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            binding?.btnSure -> register(binding?.registerAccount?.text.toString(),
                binding?.registerPwd?.text.toString())
            binding?.btnBack -> back()
        }
    }

    private fun register(username:String, password:String) {
        // Register logic
        val call = RetrofitClient.apiService.register(NetUtils.createJsonBody(mapOf("username" to username, "password" to password)))
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    val string = body?.string()
                    Log.d("TAG", "onResponse: $string")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
    }

    private fun back() {
        finish()
    }

}