package com.sattar.j.smsfake.view.navigations.sendMessage

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sattar.j.smsfake.R
import com.sattar.j.smsfake.SmsFakeApplication
import com.sattar.j.smsfake.databinding.FragmentSendMessageBinding
import org.koin.android.ext.android.inject

class SendMessageFragment : Fragment() {
    lateinit var mBinding: FragmentSendMessageBinding
    private val sendMessageVM by inject<SendMessageVM>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_send_message, container, false)
        return mBinding.root
    }

    private fun persianToast(message: String): Toast {
        return Toast.makeText(context, message, Toast.LENGTH_SHORT).also {
            val view = it.view as LinearLayout
            val tv = view.getChildAt(0) as TextView
            val typeFace = Typeface.createFromAsset(context?.assets, SmsFakeApplication.getFont(4))
            tv.typeface = typeFace
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendMessageVM.getDestinationList().observe(viewLifecycleOwner, Observer {
            Toast(context)
            persianToast(it.toString()).show()
        })

    }

}