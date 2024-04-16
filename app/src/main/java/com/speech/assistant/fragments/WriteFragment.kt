package com.speech.assistant.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.speech.assistant.base.DataChangedListener
import com.speech.assistant.base.base.BaseFragment
import com.speech.assistant.ctls.WriteCtl
import com.speech.assistant.databinding.FragmentWriteBinding

open class WriteFragment : BaseFragment<FragmentWriteBinding, WriteCtl>() {

    private val TAG = "WriteFragment"

    override fun initData() {
        super.initData()
        ctl?.getVoiceList(object : DataChangedListener<WriteCtl.VoiceData> {
            override fun onChanged(data: WriteCtl.VoiceData) {
                if (data.status == 1) {
                    // do something
                }
            }

            override fun onBefore() {
                TODO("Not yet implemented")
            }

            override fun onAfter(message: String?) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun initListener(root: View?) {
        super.initListener(root)
        binding?.send?.setOnClickListener {
            ctl?.transform(
                "text",
                "name",
                "voice",
                "volume",
                "rate",
                object : DataChangedListener<WriteCtl.WriteData> {
                    override fun onChanged(data: WriteCtl.WriteData) {
                        if (data.status == 1) {
                            // do something
                        }
                    }

                    override fun onBefore() {
                        TODO("Not yet implemented")
                    }

                    override fun onAfter(message: String?) {
                        TODO("Not yet implemented")
                    }
                })
        }

        binding?.setting?.setOnClickListener {
            ctl?.getVoiceList(object : DataChangedListener<WriteCtl.VoiceData> {
                override fun onChanged(data: WriteCtl.VoiceData) {
                    if (data.status == 1) {
                        // do something
                        Log.d(TAG, "onChanged: ${data.data?.size}")
                    }
                }

                override fun onBefore() {
                    TODO("Not yet implemented")
                }

                override fun onAfter(message: String?) {
                    TODO("Not yet implemented")
                }
            })
        }

    }


    override fun viewBindingInflater(inflater: LayoutInflater): FragmentWriteBinding {
        return FragmentWriteBinding.inflate(inflater)
    }

    override fun ctl(): WriteCtl? {
        return WriteCtl()
    }

}