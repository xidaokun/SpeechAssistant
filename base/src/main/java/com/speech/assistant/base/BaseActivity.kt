package com.speech.assistant.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding

open class BaseActivity<Binding : ViewBinding> : ComponentActivity() {

    protected var binding: Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = viewBindingInflater(layoutInflater)
        setContentView(binding?.root)
        initView(binding?.root)
        initListener(binding?.root)
        initData()
    }

    protected open fun initBarStyle() {
        // Override this method to initialize bar style
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


}