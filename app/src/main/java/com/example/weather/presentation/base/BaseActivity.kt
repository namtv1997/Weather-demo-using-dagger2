package com.example.weather.presentation.base

import android.content.Context
import android.graphics.Rect
import android.location.LocationManager
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.weather.presentation.widget.CircleProgressDialog
import com.example.weather.presentation.widget.NoNetworkDialog
import com.example.weather.utils.Constant
import com.example.weather.utils.NetworkUtils
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    private lateinit var progressDialog: CircleProgressDialog
    private lateinit var noNetworkDialog: NoNetworkDialog

    var Key= Constant.API_Key15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = CircleProgressDialog(this)
        noNetworkDialog = NoNetworkDialog(this)

        noNetworkDialog.setOnCancelListener {

            this.finish()
        }

    }

    fun showOrHideProgressDialog(isShow: Boolean) {
        when (isShow) {
            true -> progressDialog.show()
            else -> progressDialog.hide()
        }
    }

    fun showViewNoNetWork() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            noNetworkDialog.dismiss()
        } else noNetworkDialog.show()
    }

    /**
     * Close SoftKeyboard when user click out of EditText
     */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog.cancel()
    }
}