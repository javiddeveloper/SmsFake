package com.sattar.j.smsfake.view.navigations.sendMessage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
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
import org.koin.android.ext.android.inject

class SendMessageFragment : Fragment() {
    lateinit var mBinding: FragmentSendMessageBinding
    private var checkPermission = false
    private var mDialog: DialogSheet? = null
    private val sendMessageVM by inject<SendMessageVM>()


    override fun onResume() {
        super.onResume()
        sendMessageVM.getCurrentAppVersion().observe(viewLifecycleOwner, Observer {
            if (Integer.valueOf(it.versionName.replace(".", ""))
                    > Integer.valueOf(Utility.appVersionName().replace(".", "")))
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
//            Utility.persianToast(it.toString()).show()
        })

        mBinding.fabSend.setOnClickListener {
            changeDefaultSmsApp()
//            setDefaultSmsManagement()
            if (checkPermission)
                SmsTools.sendSms(true, "09178516035",
                        "test")
            context?.let { it1 -> successMessageDialog(it1) }
        }

    }

    private fun changeDefaultSmsApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && !checkPermission) {
            val myPackageName = activity?.packageName
            if (Telephony.Sms.getDefaultSmsPackage(context) != myPackageName) {
                val intent = Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT)
                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, myPackageName)
                startActivityForResult(intent, 1)
            } else {
                checkPermission = true
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            Utility.persianToast("دسترسی لغو شد لطفا مجددا سعی نمایید.").show()
            return
        }
        if (resultCode == Activity.RESULT_OK) {
            checkPermission = true
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
        mDialog?.setTitle("عملیات موفق")
        mDialog?.setMessage("برای مشاهده نتیجه وارد پیامهای خود شوید")
        mDialog?.setRoundedCorners(true)
        mDialog?.setPositiveButton("تایید", object : DialogSheet.OnPositiveClickListener {
            override fun onClick(v: View?) {
                mDialog?.dismiss()
                SmsTools.restoreDefaultSmsApp()
            }
        })
        mDialog?.show()
    }


}

