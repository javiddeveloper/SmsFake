package com.sattar.j.smsfake.view.navigations.sendMessage

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import com.sattar.j.smsfake.tools.SmsTools
import com.sattar.j.smsfake.tools.Utility
import com.sattar.j.smsfake.tools.VersionTools
import org.koin.android.ext.android.inject

class SendMessageFragment : Fragment() {
    private lateinit var mBinding: FragmentSendMessageBinding
    private var mDialog: DialogSheet? = null
    private val sendMessageVM by inject<SendMessageVM>()


    override fun onResume() {
        super.onResume()
        sendMessageVM.getCurrentAppVersion().observe(viewLifecycleOwner, Observer {
            if (VersionTools.needForUpdate(it))
                context?.let { it1 -> showUpdateDialog(it1, it) }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_send_message, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendMessageVM.getDestinationList().observe(viewLifecycleOwner, Observer {
            context?.let { it1 -> Utility.persianToast(it1,it.toString()).show() }
        })

        mBinding.fabSend.setOnClickListener {
            SmsTools.changeDefaultSmsApp(this)
            if (SmsTools.isSmsAppDefaultChanged) {
                SmsTools.sendSms(true, "09178516035",
                        "test")
                context?.let { it1 -> successMessageDialog(it1) }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            context?.let { Utility.persianToast(it,getString(R.string.access_denaed_permission_message)).show() }
            return
        }
        if (resultCode == Activity.RESULT_OK) {
            SmsTools.isSmsAppDefaultChanged = true
            SmsTools.sendSms(true, "09178516035",
                    "tes6777777jt")
            context?.let { it1 -> successMessageDialog(it1) }
        }
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

    private fun successMessageDialog(context: Context) {
        mDialog = DialogSheet(context, true)
        mDialog?.setCancelable(true)
        mDialog?.setTitleTypeface(Utility.appTypeFace(SmsFakeApplication.MEDIUM_FONT))
        mDialog?.setButtonsTypeface(Utility.appTypeFace(SmsFakeApplication.NORMAL_FONT))
        mDialog?.setMessageTypeface(Utility.appTypeFace(SmsFakeApplication.LIGHT_FONT))
        mDialog?.setPositiveButtonColorRes(R.color.colorPrimary)
        mDialog?.setIconDrawable(context.resources.getDrawable(R.drawable.sms_icon))
        mDialog?.setTitle(getString(R.string.action_success))
        mDialog?.setMessage(getString(R.string.success_message_send_sms))
        mDialog?.setRoundedCorners(true)
        mDialog?.setPositiveButton(getString(R.string.accept), object : DialogSheet.OnPositiveClickListener {
            override fun onClick(v: View?) {
                mDialog?.dismiss()
                SmsTools.restoreDefaultSmsApp()
            }
        })
        mDialog?.show()
    }


}

