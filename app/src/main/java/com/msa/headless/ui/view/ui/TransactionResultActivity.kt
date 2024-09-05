package com.msa.headless.ui.view.ui

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.color.MaterialColors
import com.minesec.msa.client.sdk.SoftPOSTransType
import com.minesec.msa.client.sdk.payment.PaymentMessage
import com.msa.headless.R
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.databinding.ActivityTransactionResultBinding
import com.msa.headless.model.TransactionResultNavigator
import com.msa.headless.ui.EXTRA_AUTH_CODE
import com.msa.headless.ui.EXTRA_CARD_BRAND
import com.msa.headless.ui.EXTRA_MASK_PAN
import com.msa.headless.ui.EXTRA_TRANS_AMOUNT
import com.msa.headless.ui.EXTRA_TRANS_ERROR_CODE_MSG
import com.msa.headless.ui.LoadingDialog
import com.msa.headless.ui.MHD_EXTRA_PAY_DESCRIPTION
import com.msa.headless.ui.MHD_EXTRA_RESPONSE_CODE
import com.msa.headless.ui.MHD_EXTRA_TRACE_NO
import com.msa.headless.ui.MHD_EXTRA_TRANS_TYPE
import com.msa.headless.ui.ReceiptQRDialog
import com.msa.headless.util.IntentUtil
import com.msa.headless.util.MessageUtils
import com.msa.headless.viewmodel.TransactionResultViewModel

class TransactionResultActivity : BaseActivity(), TransactionResultNavigator {

    private lateinit var transResultBinding: ActivityTransactionResultBinding
    private lateinit var viewModel: TransactionResultViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_result)
        transResultBinding = ActivityTransactionResultBinding.inflate(layoutInflater)
        setContentView(transResultBinding.root)
        viewModel = ViewModelProvider(this).get(TransactionResultViewModel::class.java)
        viewModel.onActivityCreated(this, intent.extras!!)
        transResultBinding.tvAmount.text = intent.getStringExtra(EXTRA_TRANS_AMOUNT) ?: ""
        transResultBinding.tvCurrency.text = MessageUtils.getTransactionCurrencySymbol(
            ApplicationConfigStore().getCurrencyText())
        when (intent.getStringExtra(MHD_EXTRA_TRANS_TYPE)) {
            SoftPOSTransType.TRANS_PRE_AUTH -> transResultBinding.txnType.setText(R.string.drawer_auth)
            SoftPOSTransType.TRANS_SALE -> transResultBinding.txnType.setText(R.string.drawer_sale)
        }
        transResultBinding.btnNewTran.setOnClickListener {
            startActivity(Intent(this,PaymentActivity::class.java))
            finish()
        }
        transResultBinding.groupReceipts.visibility = View.GONE
    }

    override fun initTransactionResultView() {
    }


    override fun showTransactionApproved() {
        transResultBinding.tvTranStatus.setText(R.string.trans_success)
        val paymentMethod = IntentUtil.getStringExtra(intent.extras, EXTRA_CARD_BRAND)
        transResultBinding.groupPmInfo.visibility = View.VISIBLE
        transResultBinding.tvError.visibility = View.GONE
        transResultBinding.tvPaymentMethod.text = paymentMethod
        transResultBinding.ivPaymentMethod.setImageResource(viewModel.getCardBrandResId(paymentMethod))

        IntentUtil.getStringExtra(intent.extras, EXTRA_MASK_PAN)?.let {
            if (it.isNotEmpty()) {
                transResultBinding.tvAccount.visibility = View.VISIBLE
                transResultBinding.tvAccount.text = it
            }
        }
        transResultBinding.tvTrace.text = getString(R.string.trace_s, IntentUtil.getStringExtra(intent.extras, MHD_EXTRA_TRACE_NO))
        transResultBinding.tvApproval.text = getString(R.string.approval_s, IntentUtil.getStringExtra(intent.extras, EXTRA_AUTH_CODE))
    }

    override fun showTransactionDeclined() {
        transResultBinding.tvTranStatus.apply {
            setText(R.string.trans_error)
            setTextColor(MaterialColors.getColor(this, R.attr.colorError))
        }
        transResultBinding.ivTranStatus.setImageResource(R.drawable.status_declined)
        transResultBinding.tvError.visibility = View.VISIBLE
        val errorCode = IntentUtil.getStringExtra(intent.extras, MHD_EXTRA_RESPONSE_CODE)
        when {
            errorCode.isNullOrEmpty() || errorCode == PaymentMessage.ONLINE_TXN_EXCEPTION_CODE -> {
                transResultBinding.tvError.text = getString(R.string.txn_declined, errorCode)
            }
            errorCode.toInt() < 0 -> {
                transResultBinding.tvError.text = getString(R.string.emv_declined, errorCode)
            }
            errorCode.toInt() > 10000 -> {
                transResultBinding.tvError.text = getString(R.string.txn_declined, errorCode)
//                ServiceErrorUtils.getActionNameByErrorCode(errorCode.toInt())?.let { errorActionName ->
//                    if (errorActionName.isNotEmpty()) {
//                        transResultBinding.btnNewTran.text = errorActionName
//                    }
//                }
            }
            else -> {
                transResultBinding.tvError.text = getString(R.string.txn_declined, errorCode)
            }
        }

        transResultBinding.tvTrace.text = getString(R.string.trace_s, IntentUtil.getStringExtra(intent.extras, MHD_EXTRA_TRACE_NO))
        transResultBinding.tvApproval.text = getString(R.string.error_desc, IntentUtil.getStringExtra(intent.extras, EXTRA_TRANS_ERROR_CODE_MSG))
        val trackNo = MessageUtils.generateRandomString(9)
        transResultBinding.tvTrack.text = getString(R.string.error_track, trackNo)
        transResultBinding.tvTrack.visibility = View.VISIBLE
        transResultBinding.groupReceipts.visibility = View.GONE
        transResultBinding.groupPmInfo.visibility = View.VISIBLE

        val paymentMethodCode = IntentUtil.getStringExtra(intent.extras, EXTRA_CARD_BRAND)
        val cardBrand = MessageUtils.formatPaymentMethodName(paymentMethodCode)
        transResultBinding.tvPaymentMethod.text = cardBrand
        val resId = viewModel.getCardBrandResId(paymentMethodCode)
        if (resId != R.drawable.acceptance_unknown) {
            transResultBinding.ivPaymentMethod.setImageResource(resId)
        }

        IntentUtil.getStringExtra(intent.extras, EXTRA_MASK_PAN)?.let {
            if (it.isNotEmpty()) {
                transResultBinding.tvAccount.visibility = View.VISIBLE
                transResultBinding.tvAccount.text = it
            }
        }
    }

    private var loadingDialog: LoadingDialog? = null

    override fun showLoadingProgress(resId: Int) {
        loadingDialog = LoadingDialog(this).apply {
            showLoadingDialog(getString(resId))
        }
    }

    override fun hideLoadingProgress() {
        loadingDialog?.hide()
        loadingDialog = null
    }

    override fun shareReceipt(receiptContent: String) {
//        val printCheck = PrintingCheck.builder().receiptContent(receiptContent).build()
//        val receipt = ReceiptBuilderBuilder().setPrintingCheck(printCheck).setContext(this).create().build()
//        val bitmap = receipt.preview()
//        val modifiedBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
//        val canvas = Canvas(modifiedBitmap)
//        // fixme - no direct color reference
//        canvas.drawColor(ContextCompat.getColor(this, R.color.basic_background))
//        canvas.drawBitmap(bitmap, 0f, 0f, null)
//        val shareIntent = Intent(Intent.ACTION_SEND).apply {
//            type = "image/png"
//            val bmpUri = viewModel.saveImage(modifiedBitmap, this@TransResultActivity)
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            putExtra(Intent.EXTRA_SUBJECT, "Receipt")
//            putExtra(Intent.EXTRA_STREAM, bmpUri)
//        }
//        startActivity(Intent.createChooser(shareIntent, "Share image"))
    }

    override fun displayError(resId: Int, errorMessage: String) {
        alertOnly(String.format(getString(resId), errorMessage))
    }

    override fun displayQrCode(bitmap: Bitmap) {
        ReceiptQRDialog(this).apply {
            setQrCodeImg(bitmap)
            show()
        }
    }

    override fun finish(l: Long) {}
}