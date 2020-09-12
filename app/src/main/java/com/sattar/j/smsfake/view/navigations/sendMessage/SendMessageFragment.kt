package com.sattar.j.smsfake.view.navigations.sendMessage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.content.ContextCompat.getColor
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.marcoscg.dialogsheet.DialogSheet
import com.sattar.j.smsfake.R
import com.sattar.j.smsfake.SmsFakeApplication
import com.sattar.j.smsfake.data.entity.SmsAction
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
    private val smsAction = SmsAction()

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
        mBinding.vm = sendMessageVM
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendMessageVM.getDestinationList().observe(viewLifecycleOwner, Observer {
            context?.let { it1 -> Utility.persianToast(it1, it.toString()).show() }
        })

        mBinding.fabSend.setOnClickListener {
            SmsTools.changeDefaultSmsApp(this)
            if (SmsTools.isSmsAppDefaultChanged) {
                if (validationForm()) {
                    smsAction.isReceive = mBinding.radioReceive.isChecked
                    smsAction.isReceive = mBinding.radioReceive.isActivated
                    smsAction.phoneNumber = mBinding.editDestination.text.toString()
                    smsAction.message = mBinding.editMessage.text.toString()
                    SmsTools.sendSms(smsAction)
                    context?.let { it1 -> successMessageDialog(it1) }
                }
            }
        }

        mBinding.editMessage.addTextChangedListener {
            object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (p0!!.isEmpty()) {
                        mBinding.editMessage.hint = getString(R.string.err_text_message)
                        mBinding.editMessage.setHintTextColor(getColor(context!!, R.color.errColor))
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    mBinding.editMessage.hint = getString(R.string.messageText)
                    mBinding.editMessage.setHintTextColor(getColor(context!!, R.color.hintColor))
                }
            }
        }

//        mBinding.txtTime.clicks().buffer(600, TimeUnit.MILLISECONDS, 2).filter {
//            Log.d( "onViewCreated: ",it.size.toString())
//            it.size >= 2
//        }.observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    context?.let { it1 -> Utility.persianToast(it1, "fdjkdlfghdkj").show() }
//                }
        var doubleTap = false
        mBinding.txtTime.setOnClickListener {
            if (doubleTap)
                timeDialog(it.context)
            doubleTap = true
            Handler(Looper.getMainLooper()).postDelayed({
                doubleTap = false
            }, 1500)
        }
        mBinding.txtDate.setOnClickListener {
            if (doubleTap)
                dateDialog(it.context)
            doubleTap = true
            Handler(Looper.getMainLooper()).postDelayed({
                doubleTap = false
            }, 1500)
        }
    }

    private fun validationForm(): Boolean {
        return when {
            mBinding.editDestination.text!!.isEmpty() -> {
                mBinding.editDestination.error = getString(R.string.err_enter_destination)
                false
            }
            mBinding.editMessage.text!!.isEmpty() -> {
                mBinding.editMessage.hint = getString(R.string.err_text_message)
                mBinding.editMessage.setHintTextColor(getColor(SmsFakeApplication.appContext!!, R.color.errColor))
                false
            }
            else -> true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            context?.let { Utility.persianToast(it, getString(R.string.access_denaed_permission_message)).show() }
            return
        }
        if (resultCode == Activity.RESULT_OK) {
            SmsTools.isSmsAppDefaultChanged = true
            SmsTools.sendSms(smsAction)
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

    private fun timeDialog(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.time_picker, null, false)
        val timePicker: TimePicker = view.findViewById(R.id.timePicker)
        timePicker.setIs24HourView(true)
        timePicker.setOnTimeChangedListener(object : TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(p0: TimePicker?, p1: Int, p2: Int) {
                smsAction.time = "$p1:$p2"
            }
        })
        mDialog = DialogSheet(context, true)
        mDialog?.setCancelable(true)
        mDialog?.setTitleTypeface(Utility.appTypeFace(SmsFakeApplication.MEDIUM_FONT))
        mDialog?.setButtonsTypeface(Utility.appTypeFace(SmsFakeApplication.NORMAL_FONT))
        mDialog?.setMessageTypeface(Utility.appTypeFace(SmsFakeApplication.LIGHT_FONT))
        mDialog?.setPositiveButtonColorRes(R.color.colorPrimary)
        mDialog?.setIconDrawable(context.resources.getDrawable(R.drawable.sms_icon))
        mDialog?.setTitle(getString(R.string.time_send))
        mDialog?.setView(view)
        mDialog?.setRoundedCorners(true)
        mDialog?.setPositiveButton(getString(R.string.accept), object : DialogSheet.OnPositiveClickListener {
            override fun onClick(v: View?) {
                mDialog?.dismiss()
//                mBinding.txtTime.text = smsAction.time
                sendMessageVM.setSmsAction(smsAction)
//                mBinding.vm = sendMessageVM
            }
        })
        mDialog?.show()
    }
    private fun dateDialog(context: Context) {

        mDialog = DialogSheet(context, true)
        mDialog?.setCancelable(true)
        mDialog?.setTitleTypeface(Utility.appTypeFace(SmsFakeApplication.MEDIUM_FONT))
        mDialog?.setButtonsTypeface(Utility.appTypeFace(SmsFakeApplication.NORMAL_FONT))
        mDialog?.setMessageTypeface(Utility.appTypeFace(SmsFakeApplication.LIGHT_FONT))
        mDialog?.setPositiveButtonColorRes(R.color.colorPrimary)
        mDialog?.setIconDrawable(context.resources.getDrawable(R.drawable.sms_icon))
        mDialog?.setTitle(getString(R.string.date_send))

        mDialog?.setRoundedCorners(true)
        mDialog?.setPositiveButton(getString(R.string.accept), object : DialogSheet.OnPositiveClickListener {
            override fun onClick(v: View?) {
                mDialog?.dismiss()
            }
        })
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val view = LayoutInflater.from(context).inflate(R.layout.date_picker, null, false)
            val calendarView: DatePicker = view.findViewById(R.id.datePicker)
            calendarView.setOnDateChangedListener { _, i, i2, i3 ->
                Log.d( "dateDialog: ","$i $i2 $i3")
            }
            mDialog?.setView(view)
        }
        mDialog?.show()
    }


}

