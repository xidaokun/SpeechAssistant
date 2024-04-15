package com.speech.assistant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.speech.assistant.base.BaseActivity
import com.speech.assistant.databinding.ActivityRegisterBinding
import com.speech.assistant.datas.SResponse
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
                binding?.registerPwd?.text.toString(), binding?.registerConfirmPwd?.text.toString())
            binding?.btnBack -> back()
        }
    }

    private fun register(username:String, password:String, confirmPassword:String) {
        if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this@RegisterActivity, "account or password is empty!", Toast.LENGTH_SHORT).show()
            return
        }

        if((password != confirmPassword)) {
            Toast.makeText(this@RegisterActivity, "password is not same!", Toast.LENGTH_SHORT).show()
            return
        }

        showWaiting()
        // Register logic
        val call = RetrofitClient.apiService.register(NetUtils.createJsonBody(mapOf("name" to username, "password" to password)))
        call.enqueue(object : Callback<SResponse<Any>> {
            override fun onResponse(call: Call<SResponse<Any>>, response: Response<SResponse<Any>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("TAG", "onResponse: ${body?.message}")
                    Toast.makeText(this@RegisterActivity, "register success!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                dismissWaiting()
            }

            override fun onFailure(call: Call<SResponse<Any>>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
                dismissWaiting()
                Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun back() {
        finish()
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
}