package com.msa.headless.ui.view.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.msa.headless.R
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.model.Navigator


abstract class BaseActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        if (!ApplicationConfigStore().getEnableScreenshotFlag()) {
            window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        }
    }

    override fun alert(resId: Int) {
        alert(getString(resId))
    }

    override fun alert(tr: Throwable) {
        tr.message?.let { alert(it) }
    }

    override fun alert(message: String) {
        MaterialAlertDialogBuilder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.btn_dismiss) { dialogInterface, _ ->
                dialogInterface.dismiss()
                finish()
            }
            .show()
    }

    override fun alertOnly(message: String) {
        MaterialAlertDialogBuilder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.btn_dismiss) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .show()
    }

    override fun alertWithListener(message: String, runnable: Runnable) {
        MaterialAlertDialogBuilder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.btn_dismiss) { dialogInterface, _ ->
                dialogInterface.dismiss()
                runnable.run()
            }
            .show()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_UP) {
            val view = currentFocus
            if (view is EditText) {
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    view.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

}
