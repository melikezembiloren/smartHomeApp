package com.example.awoxapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.awoxapp.R
import com.example.awoxapp.databinding.FragmentMemberShipAgreementBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MemberShipAgreementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MemberShipAgreementDialogFragment : DialogFragment() {

    private lateinit var mBinding: FragmentMemberShipAgreementBinding


    private fun loadTextWebView(){

        val webView : WebView = mBinding.webView
        val url = "https://www.awox.com.tr/UyelikSozlesme.aspx"
        webView.loadUrl(url)

    }

    private fun closeButtonClicked(){
        mBinding.closeButton.setOnClickListener{dismiss()}
    }

    private fun navigatePersonalDataFragment(){
        val dialogFragment = ProtectionOfPersonalDataDialogFragment()
        dialogFragment.show(childFragmentManager, "ProtectionOfPersonalDataFragment")


    }

    private fun acceptButtonClicked(){
        mBinding.membershipAgreementAcceptButton.setOnClickListener{navigatePersonalDataFragment()}
    }


    private fun initialize(){
        loadTextWebView()
        acceptButtonClicked()
        closeButtonClicked()
        mBinding.acceptTextsTitle = getString(R.string.accept_membershipr_agreement_title)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_member_ship_agreement, container, false)



        initialize()
        return mBinding.root







    }
}