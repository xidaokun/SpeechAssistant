package com.speech.assistant

import android.view.LayoutInflater
import android.view.View
import com.speech.assistant.base.BaseActivity
import com.speech.assistant.databinding.ActivityMainBinding
import com.speech.assistant.fragments.FavorFragment
import com.speech.assistant.fragments.MeFragment
import com.speech.assistant.fragments.SayFragment
import com.speech.assistant.fragments.WriteFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val writeFragment = WriteFragment()
    val sayFragment = SayFragment()
    val favorFragment = FavorFragment()
    val meFragment = MeFragment()

    override fun viewBindingInflater(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initData() {
        super.initData()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, writeFragment)
            add(R.id.fragment_container, sayFragment)
            add(R.id.fragment_container, favorFragment)
            add(R.id.fragment_container, meFragment)
            hide(sayFragment)
            hide(favorFragment)
            hide(meFragment)
        }.commit()
    }

    override fun initListener(root: View?) {
        super.initListener(root)
        binding?.bottomNavigation?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_write -> {
                    supportFragmentManager.beginTransaction().apply {
                        show(writeFragment)
                        hide(sayFragment)
                        hide(favorFragment)
                        hide(meFragment)
                    }.commit()
                }
                R.id.nav_say -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(writeFragment)
                        show(sayFragment)
                        hide(favorFragment)
                        hide(meFragment)
                    }.commit()
                }
                R.id.nav_favor -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(writeFragment)
                        hide(sayFragment)
                        show(favorFragment)
                        hide(meFragment)
                    }.commit()
                }
                R.id.nav_me -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(writeFragment)
                        hide(sayFragment)
                        hide(favorFragment)
                        show(meFragment)
                    }.commit()
                }
            }
            true
        }
    }


}