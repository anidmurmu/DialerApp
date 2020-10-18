package com.example.ui.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ui.R

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var isViewAlive: Boolean = false
    private var isViewInteractive: Boolean = false

    private var dynamicViewHandler: DynamicViewHandler? = null

    override fun closeKeyBoard() {
        if (currentFocus != null && currentFocus?.windowToken != null) {
            val inputManager = getSystemService(Context
                    .INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    fun showToast(msg: String?) {
        msg?.also {
            window?.decorView?.showToast(it)
        }
    }

    override fun showProgress() {
        if (dynamicViewHandler == null) {
            dynamicViewHandler = DynamicViewHandler(this)
        }
        dynamicViewHandler!!.show(R.layout.progress)
        closeKeyBoard()
    }

    override fun hideProgress() {
        dynamicViewHandler?.hide()
    }

    override fun showMessage(message: String?) {
        showToast(message)
    }

    override fun isViewAlive() = isViewAlive

    override fun isViewInteractive() = isViewInteractive

    fun getDisplayMetrics(): DisplayMetrics {
        val display = windowManager.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        return metrics
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onStart() {
        super.onStart()
        isViewInteractive = true
    }

    override fun onStop() {
        super.onStop()
        isViewInteractive = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isViewAlive = true
    }

    override fun onDestroy() {
        super.onDestroy()
        isViewAlive = false
    }

    override fun showInternetError() {
        if (dynamicViewHandler == null) {
            dynamicViewHandler = DynamicViewHandler(this)
        }
        val view = dynamicViewHandler!!.show(R.layout.layout_internet_error)
        val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT)
        params.setMargins(0, 0, 0, 0)
        view.layoutParams = params
        closeKeyBoard()
    }

    override fun hideInternetError() {
        dynamicViewHandler?.hide()
    }

    override fun showServerError(errorCode: String, userId: String, versionCode: String) {
        if (dynamicViewHandler == null) {
            dynamicViewHandler = DynamicViewHandler(this)
        }
        val view = dynamicViewHandler!!.show(R.layout.layout_server_error)
        val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT)
        params.setMargins(0, 0, 0, 0)
        view.layoutParams = params
        closeKeyBoard()
    }

    override fun hideServerError() {
        dynamicViewHandler?.hide()
    }

    override fun isViewAvailable() = !isFinishing

    fun View.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this.context, message, duration).show()
    }
}
