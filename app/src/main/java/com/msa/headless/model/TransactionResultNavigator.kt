package com.msa.headless.model

import android.graphics.Bitmap

/**
 * @author eric.song
 * @since 2024/9/1 18:05
 */
interface TransactionResultNavigator : Navigator {
    fun initTransactionResultView()

    fun showTransactionApproved()

    fun showTransactionDeclined()

    fun showLoadingProgress(resId: Int)

    fun hideLoadingProgress()

    fun shareReceipt(receiptContent: String)

    fun displayError(resId: Int, errorMessage: String)

    fun displayQrCode(bitmap: Bitmap)

    fun finish(l: Long)
}