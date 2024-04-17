package com.speech.assistant.fragments

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.speech.assistant.MyConstants
import com.speech.assistant.R
import com.speech.assistant.base.DataChangedListener
import com.speech.assistant.base.base.BaseFragment
import com.speech.assistant.base.utils.PreferenceHelper
import com.speech.assistant.ctls.WriteCtl
import com.speech.assistant.databinding.FragmentWriteBinding
import java.text.SimpleDateFormat
import java.util.Date

open class WriteFragment : BaseFragment<FragmentWriteBinding, WriteCtl>() {

    private val TAG = "WriteFragment"

    override fun initData() {
        super.initData()
        activity?.findViewById<EditText>(R.id.setting_rate_edit)?.setText(PreferenceHelper.getString(MyConstants.SP_VOICE_VOLUME)?: "50")
        activity?.findViewById<EditText>(R.id.setting_volume_edit)?.setText(PreferenceHelper.getString(MyConstants.SP_VOICE_RATE)?: "50")
        ctl?.getVoiceList(object : DataChangedListener<WriteCtl.VoiceData> {
            override fun onChanged(data: WriteCtl.VoiceData) {
                if (data.status == 1) {
                    // do something
                    Log.d(TAG, "onChanged: ${data.data?.size}")
                    val shortNames = data.data?.map { it.ShortName }?.toMutableList() ?: mutableListOf()
                    shortNames.add(0, "--请选择--")
                    var adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, shortNames)
                    activity?.findViewById<Spinner>(R.id.voice_spinner)?.adapter = adapter
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

    @SuppressLint("SimpleDateFormat")
    override fun initListener(root: View?) {
        super.initListener(root)
        val text = binding?.content?.text.toString()
        val rate = PreferenceHelper.getString(MyConstants.SP_VOICE_RATE)?: "50"
        val volume = PreferenceHelper.getString(MyConstants.SP_VOICE_VOLUME)?: "50"
        val voice = PreferenceHelper.getString(MyConstants.SP_VOICE_VOLUME)?: "zh-CN-YunxiNeural"

        binding?.send?.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy-MM-dd_HH:mm:ss")
            val name = sdf.format(Date())
            ctl?.transform(text, name, voice, "+$volume%", "-$rate%",
                object : DataChangedListener<WriteCtl.WriteData> {
                    override fun onChanged(data: WriteCtl.WriteData) {
                        if (data.status == 1) {
                            ctl?.downloadFile("", name, object : DataChangedListener<WriteCtl.WriteData> {
                                override fun onChanged(data: WriteCtl.WriteData) {
                                    if (data.status == 1) {
                                        Log.d(TAG, "onChanged: ${data.message}")
                                    }
                                }

                                override fun onBefore() {
                                    Log.d(TAG, "onBefore: ")
                                }

                                override fun onAfter(message: String?) {
                                    Log.d(TAG, "onAfter: $message")
                                }
                            })
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
            activity?.findViewById<View>(R.id.write_setting)?.visibility = View.VISIBLE
        }

        activity?.findViewById<View>(R.id.write_sure_btn)?.setOnClickListener {
            activity?.findViewById<View>(R.id.write_setting)?.visibility = View.GONE
            activity?.findViewById<EditText>(R.id.setting_volume_edit)?.text.let {
                PreferenceHelper.save(MyConstants.SP_VOICE_VOLUME, it.toString())
            }

            activity?.findViewById<EditText>(R.id.setting_rate_edit)?.text.let {
                PreferenceHelper.save(MyConstants.SP_VOICE_RATE, it.toString())
            }
        }

        activity?.findViewById<View>(R.id.write_cancel_btn)?.setOnClickListener {
            activity?.findViewById<View>(R.id.write_setting)?.visibility = View.GONE
        }
    }


    override fun viewBindingInflater(inflater: LayoutInflater): FragmentWriteBinding {
        return FragmentWriteBinding.inflate(inflater)
    }

    override fun ctl(): WriteCtl? {
        return WriteCtl()
    }

}