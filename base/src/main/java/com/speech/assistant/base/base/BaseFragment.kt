package com.speech.assistant.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<Binding : ViewBinding, Ctl : BaseCtl> : Fragment() {
    protected var binding: Binding? = null
    protected var ctl: Ctl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ctl = ctl()
        ctl?.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = viewBindingInflater(layoutInflater)
        initView(binding?.root)
        initListener(binding?.root)
        initData()
        ctl?.onCreateView()
        return binding?.root
    }

    protected open fun viewBindingInflater(inflater: LayoutInflater): Binding? {
        return null
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        ctl?.onDestroyView()
        ctl = null
    }

    protected open fun ctl(): Ctl? {
        return null
    }
}