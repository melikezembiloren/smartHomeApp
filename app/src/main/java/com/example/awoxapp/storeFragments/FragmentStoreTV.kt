package com.example.awoxapp.storeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.Constants
import com.example.awoxapp.R
import com.example.awoxapp.databinding.FragmentStoreTVBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentStoreTV : BottomSheetDialogFragment() {

    private lateinit var mBinding: FragmentStoreTVBinding


    private fun showWebView(){

       val webView: WebView =  mBinding.webViewTvStore
        webView.loadUrl(Constants.URL_TV)
    }

    private fun initialize(){
        showWebView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_t_v, container, false)

        initialize()
        // Inflate the layout for this fragment
        return mBinding.root
    }

}