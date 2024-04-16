package com.speech.assistant.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<Binding : ViewBinding, Ctl : BaseCtl> : AppCompatActivity() {

    protected var binding: Binding? = null
    protected var ctl: Ctl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = viewBindingInflater(layoutInflater)
        ctl = ctl()
        ctl?.onCreate()
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

    override fun onResume() {
        super.onResume()
        ctl?.onResume()
    }

    protected open fun ctl(): Ctl? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        ctl?.onDestroy()
        ctl = null
    }

}