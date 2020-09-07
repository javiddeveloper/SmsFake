package com.sattar.j.smsfake.view.navigations

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.sattar.j.smsfake.R
import com.sattar.j.smsfake.databinding.FragmentSplashBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashFragment : Fragment() {
    lateinit var mBinding: FragmentSplashBinding
    lateinit var composit: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mBinding.background.setImageBlur(R.drawable.sms)
        } else {
            mBinding.background.setImage(R.drawable.sms)
        }
        composit = CompositeDisposable()
        composit.add(Observable.just(0)
                .delay(4, TimeUnit.SECONDS)
                .doOnNext {
                    Navigation.findNavController(mBinding.root)
                            .navigate(R.id.action_splashFragment_to_sendMessageFragment)
                }
                .subscribe ())

    }

    override fun onDestroy() {
        composit.dispose()
        super.onDestroy()
    }

}