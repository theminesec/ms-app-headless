package com.msa.headless.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.minesec.msa.client.sdk.payment.PaymentMethods
import com.msa.headless.R
import com.msa.headless.model.Navigator
import com.msa.headless.model.TransactionResultNavigator
import com.msa.headless.ui.EXTRA_TRANS_STATUS
import com.msa.headless.util.IntentUtil
import com.theminesec.sdk.headless.model.transaction.PaymentMethod

/**
 * @author eric.song
 * @since 2024/9/1 17:56
 */
class TransactionResultViewModel : ViewModel() {

    private lateinit var transactionResultNavigator: TransactionResultNavigator

    fun onActivityCreated(navigator: TransactionResultNavigator,bundle: Bundle) {
        this.transactionResultNavigator = navigator
        if (IntentUtil.getBooleanExtra(bundle, EXTRA_TRANS_STATUS)) {
            navigator.showTransactionApproved()
        } else {
            navigator.showTransactionDeclined()
        }
    }

    fun getCardBrandResId(paymentMethod: String): Int {
        return when (paymentMethod) {
            PaymentMethod.VISA.name -> R.drawable.acceptance_visa
            PaymentMethod.MASTERCARD.name -> R.drawable.acceptance_mastercard
            PaymentMethod.AMEX.name -> R.drawable.acceptance_amex
            PaymentMethod.UNIONPAY.name -> R.drawable.acceptance_unionpay
            PaymentMethod.JCB.name -> R.drawable.acceptance_jcb
            PaymentMethod.DISCOVER.name -> R.drawable.acceptance_discover
            else -> R.drawable.acceptance_unknown
        }
    }
}