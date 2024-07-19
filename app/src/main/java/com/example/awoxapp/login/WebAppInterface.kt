package com.example.awoxapp.login

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast


class WebAppInterface (c: Context) {
    var mContext: Context = c

    // Show a toast from the web page
    @JavascriptInterface
    fun showToast(message: String?) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

    }

    @JavascriptInterface
    fun onScrollToBottom() {
        // Handle scroll to bottom event here
        Toast.makeText(mContext, "Scrolled to Bottom", Toast.LENGTH_SHORT).show()
        Log.d("web","scroll")
    }
}
