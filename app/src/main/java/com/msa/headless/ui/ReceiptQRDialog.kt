package com.msa.headless.ui

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.msa.headless.R
import com.msa.headless.databinding.DialogQrReceiptBinding

/**
 * @author eric.song
 * @since 2024/9/1 19:36
 */
class ReceiptQRDialog(context: Context) : MaterialAlertDialogBuilder(context) {

    private val binding: DialogQrReceiptBinding =
        DialogQrReceiptBinding.inflate(LayoutInflater.from(context))

    init {
        setTitle(R.string.title_receipt_qr)
        setView(binding.root)
        setPositiveButton(R.string.btn_dismiss) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
    }

    fun setQrCodeImg(bitmap: Bitmap?): ReceiptQRDialog {
        binding.ivQrCode.setImageBitmap(bitmap)
        return this
    }
}