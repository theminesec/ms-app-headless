package com.msa.headless.ui

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.msa.headless.databinding.DialogLoadingBinding

/**
 * @author eric.song
 * @since 2024/9/1 18:38
 */
class LoadingDialog(private val activity: Activity) {
    private var dialog: AlertDialog? = null

    fun showLoadingDialog(message: String) {
        val binding = DialogLoadingBinding.inflate(activity.layoutInflater)
        binding.tvLoadingDesc.text = message

        dialog = MaterialAlertDialogBuilder(activity)
            .setView(binding.root)
            .setCancelable(false)
            // .setNeutralButton("Dismiss") { dialogInterface, i -> dialog?.dismiss() }
            .create()

        dialog?.show()
    }

    fun dismissLoadingDialog() {
        dialog?.dismiss()
    }

    fun hide() {
        dialog?.dismiss()
    }
}