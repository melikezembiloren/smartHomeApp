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
import com.example.awoxapp.databinding.FragmentProtectionOfPersonalDataBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MemberShipAgreementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProtectionOfPersonalDataDialogFragment : DialogFragment() {

    private lateinit var mBinding: FragmentProtectionOfPersonalDataBinding


    private fun loadTextWebView(){

        val webView : WebView = mBinding.webView
        val url = "https://www.awox.com.tr/UyelikSozlesme.aspx?sozlemeTipi=5"
        webView.loadUrl(url)

    }

    private fun closeButtonClicked() {
        mBinding.closeButton.setOnClickListener{dismiss()} //dismiss() dialoğu kapatır
    }


    private fun initialize(){
        loadTextWebView()


      //  val a: Users = requireActivity().intent!!.getSerializableExtra("KEY") as Users

       // Toast.makeText(activity,a.userFirstName,Toast.LENGTH_SHORT).show()
        closeButtonClicked()
        mBinding.acceptTextsTitle = getString(R.string.accept_protection_of_personal_data_title)

       // val a =requireArguments().getString(EDIT_TEXT_PASSWORD)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_protection_of_personal_data, container, false)

        initialize()
        return mBinding.root







    }
}