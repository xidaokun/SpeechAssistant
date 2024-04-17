package com.speech.assistant.fragments

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

open class WriteFragment : BaseFragment<FragmentWriteBinding, WriteCtl>() {

    private val TAG = "WriteFragment"

    override fun initData() {
        super.initData()
        activity?.findViewById<EditText>(R.id.setting_rate_edit)?.setText(PreferenceHelper.getString(MyConstants.SP_VOICE_RATE)?: "50")
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