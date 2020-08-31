package com.sattar.j.smsfake.view.navigations.sendMessage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sattar.j.smsfake.R
import com.sattar.j.smsfake.databinding.FragmentSendMessageBinding
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.Koin
import org.koin.core.context.KoinContextHandler.get

class SendMessageFragment : Fragment() {
    lateinit var mBinding: FragmentSendMessageBinding
//     private val sendMessageVM : SendMessageVM by viewModel()
//     private val sendMessageVM = get<SendMessageVM>()
     private val sendMessageVM by inject<SendMessageVM>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_send_message, container, false)
//        sendMessageVM = ViewModelProvider(this).get(SendMessageVM::class.java)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendMessageVM.getDestinationList().observe(viewLifecycleOwner, Observer {
            Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
        })

    }

}