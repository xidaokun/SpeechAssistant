package com.speech.assistant.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.speech.assistant.BaseFragment

open class BaseActivity<Binding : ViewBinding> : AppCompatActivity() {

    protected var binding: Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = viewBindingInflater(layoutInflater)
        setContentView(binding?.root)
        initView(binding?.root)
        initListener(binding?.root)
        initData()
    }

    protected open fun initView(root : View?) {
        // Override this method to initialize view
    }

    protected open fun initListener(root: View?) {
        // Override this method to initialize listener
    }

    protected open fun initData() {
        // Override this method to initialize data
    }

    protected open fun viewBindingInflater(inflater: LayoutInflater): Binding? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}