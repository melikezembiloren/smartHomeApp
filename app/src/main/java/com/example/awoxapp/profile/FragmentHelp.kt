package com.example.awoxapp.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.Constants
import com.example.awoxapp.R
import com.example.awoxapp.databinding.FragmentHelpBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentHelp: BottomSheetDialogFragment() {

    private lateinit var mBinding: FragmentHelpBinding


    private fun showWebView(){

       val webView: WebView =  mBinding.webViewAwoxHelp
        webView.loadUrl(Constants.URL_HELP)
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_help, container, false)

        initialize()
        // Inflate the layout for this fragment
        return mBinding.root
    }

}