package com.example.awoxapp.login

import android.app.AlertDialog
import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Button
import android.widget.Toast


class WebAppInterface(c: Context, val button: Button) {
    var mContext: Context = c

    // Show a toast from the web page
    @JavascriptInterface
    fun showToast(message: String?) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

    }

    @JavascriptInterface
    fun onScrollToBottom() {
        // Handle scroll to bottom event here
        button.isEnabled = true
        Toast.makeText(mContext, "Scrolled to Bottom", Toast.LENGTH_SHORT).show()
    }
}
