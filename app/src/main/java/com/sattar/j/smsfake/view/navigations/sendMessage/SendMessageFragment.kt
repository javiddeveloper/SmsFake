package com.sattar.j.smsfake.view.navigations.sendMessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sattar.j.smsfake.R
import com.sattar.j.smsfake.databinding.FragmentSendMessageBinding
import com.sattar.j.smsfake.view.navigations.sendMessage.sendMessage.SendMessageVM
import org.koin.android.ext.android.inject

class SendMessageFragment : Fragment() {
//    private lateinit var destinationAdapter: DestinationAdapter
    lateinit var mBinding: FragmentSendMessageBinding
    private val sendMessageVM by inject<SendMessageVM>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_send_message, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendMessageVM.getDestinationList().observe(viewLifecycleOwner, Observer {
            val list = ArrayList<String>()
            for (destination in it) {
                list.add(destination.name)
            }
            val adapter:ArrayAdapter<String> = ArrayAdapter(view.context,R.layout.item_destination_layout,R.id.txt_name,list)
            mBinding.autoDestination.threshold = 1
//            mBinding.autoDestination.
            mBinding.autoDestination.setAdapter(adapter)

//            Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
//            if (destinationAdapter.items.size > 0) {
//                destinationAdapter = DestinationAdapter(view.context, R.layout.item_destination_layout, it as ArrayList<Destination>)
//                mBinding.autoDestination.threshold = 1
//                mBinding.autoDestination.setAdapter(destinationAdapter)
//            }
        })

    }

}