package com.sattar.j.smsfake.view.navigations.sendMessage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.marcoscg.dialogsheet.DialogSheet
import com.sattar.j.smsfake.R
import com.sattar.j.smsfake.SmsFakeApplication
import com.sattar.j.smsfake.data.entity.Version
import com.sattar.j.smsfake.databinding.FragmentSendMessageBinding
import com.sattar.j.smsfake.tools.Utility
import org.koin.android.ext.android.inject

class SendMessageFragment : Fragment() {
    lateinit var mBinding: FragmentSendMessageBinding
    private var mDialog : DialogSheet? = null
    private val sendMessageVM by inject<SendMessageVM>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_send_message, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendMessageVM.getDestinationList().observe(viewLifecycleOwner, Observer {
//            Utility.persianToast(it.toString()).show()
        })
    }

    override fun onResume() {
        super.onResume()
        sendMessageVM.getCurrentAppVersion().observe(viewLifecycleOwner, Observer {
            if (Integer.valueOf(it.versionName.replace(".", ""))
                    > Integer.valueOf(Utility.appVersionName().replace(".", "")))
                context?.let { it1 -> showUpdateDialog(it1, it) }
        })
    }

    private fun showUpdateDialog(context: Context, version: Version) {
        val isForceUpdate = Integer.valueOf(version.isForce) == 1
        mDialog = DialogSheet(context, true)
        mDialog?.setCancelable(!isForceUpdate)
        mDialog?.setTitleTypeface(Utility.appTypeFace(SmsFakeApplication.MEDIUM_FONT))
        mDialog?.setButtonsTypeface(Utility.appTypeFace(SmsFakeApplication.NORMAL_FONT))
        mDialog?.setMessageTypeface(Utility.appTypeFace(SmsFakeApplication.LIGHT_FONT))
        mDialog?.setPositiveButtonColorRes(R.color.colorPrimary)
        mDialog?.setIconDrawable(context.resources.getDrawable(R.drawable.sms_icon))
        mDialog?.setTitle(getString(R.string.update_version))
        mDialog?.setMessage(version.releaseNote)
        mDialog?.setRoundedCorners(true)
        mDialog?.setPositiveButton(getString(R.string.update_myket), object : DialogSheet.OnPositiveClickListener {
            override fun onClick(v: View?) {
                mDialog?.dismiss()
                Utility.openUrl(version.myketLink)
            }
        })
        mDialog?.setNegativeButton(if (isForceUpdate) getString(R.string.exit_app) else getString(R.string.cancel), object : DialogSheet.OnNegativeClickListener {
            override fun onClick(v: View?) {
                if (isForceUpdate) {
                    activity?.finish()
                } else {
                    mDialog?.dismiss()
                }
            }
        })
        mDialog?.show()
    }
}