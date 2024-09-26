package com.msa.headless.ui.view.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.color.MaterialColors
import com.minesec.msa.client.sdk.SoftPOSTransType
import com.msa.headless.R
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.databinding.ActivityPaymentBinding
import com.msa.headless.model.Navigator
import com.msa.headless.ui.EXTRA_CARD_BRAND
import com.msa.headless.ui.EXTRA_PAY_TRANSACTION_ID
import com.msa.headless.ui.EXTRA_TRANS_AMOUNT
import com.msa.headless.ui.EXTRA_TRANS_ERROR_CODE_MSG
import com.msa.headless.ui.EXTRA_TRANS_STATUS
import com.msa.headless.ui.EXTRA_TRANS_TC
import com.msa.headless.ui.MHD_EXTRA_ACQ_MERCHANT_ID
import com.msa.headless.ui.MHD_EXTRA_ACQ_TERMINAL_ID
import com.msa.headless.ui.MHD_EXTRA_AID
import com.msa.headless.ui.MHD_EXTRA_AUTH_CODE
import com.msa.headless.ui.MHD_EXTRA_BATCH_NO
import com.msa.headless.ui.MHD_EXTRA_CREATE_TIME
import com.msa.headless.ui.MHD_EXTRA_ENTRY_MODE
import com.msa.headless.ui.MHD_EXTRA_MESSAGE_ID
import com.msa.headless.ui.MHD_EXTRA_PAY_DESCRIPTION
import com.msa.headless.ui.MHD_EXTRA_PAY_METHOD
import com.msa.headless.ui.MHD_EXTRA_REFERENCE_NO
import com.msa.headless.ui.MHD_EXTRA_RESPONSE_CODE
import com.msa.headless.ui.MHD_EXTRA_TRACE_NO
import com.msa.headless.ui.MHD_EXTRA_TRANS_TSI
import com.msa.headless.ui.MHD_EXTRA_TRANS_TVR
import com.msa.headless.ui.MHD_EXTRA_TRANS_TYPE
import com.msa.headless.ui.TAG
import com.msa.headless.util.DrawerUtils
import com.msa.headless.util.MessageUtils
import com.msa.headless.viewmodel.PaymentViewModel
import com.theminesec.sdk.headless.HeadlessActivity
import com.theminesec.sdk.headless.model.WrappedResult
import com.theminesec.sdk.headless.model.transaction.Amount
import com.theminesec.sdk.headless.model.transaction.CvmSignatureMode
import com.theminesec.sdk.headless.model.transaction.PoiRequest
import com.theminesec.sdk.headless.model.transaction.TranType
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Currency
import java.util.Locale
import java.util.UUID

class PaymentActivity : BaseActivity(),Navigator {

    private lateinit var viewModel: PaymentViewModel
    private lateinit var paymentBinding: ActivityPaymentBinding
    private lateinit var resultLauncher:ActivityResultLauncher<PoiRequest>
    private var amount: String = ""
    private var totalAmount: String = ""
    private var txnType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[PaymentViewModel::class.java]
        viewModel.onActivityCreated(this)
        // Inflate binding and set content view
        paymentBinding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(paymentBinding.root)
        // Init txn type
        txnType = intent.getStringExtra(MHD_EXTRA_TRANS_TYPE)
        if (txnType != null && SoftPOSTransType.TRANS_PRE_AUTH == txnType) {
            paymentBinding.appBarMain.txnType.setText(R.string.drawer_auth)
        } else {
            txnType = SoftPOSTransType.TRANS_SALE
        }
        DrawerUtils.checkMenuOptions(paymentBinding.navView.navigationFirstView)
        paymentBinding.navView.navigationFirstView.setNavigationItemSelectedListener { item ->
            DrawerUtils.selectNavigationDrawerItem(this, paymentBinding.drawerLayout, item, txnType)
            true
        }
        paymentBinding.navView.navigationSecondView.setNavigationItemSelectedListener { item ->
            DrawerUtils.selectNavigationDrawerItem(this, paymentBinding.drawerLayout, item, txnType)
            true
        }
        DrawerUtils.setNavigationDrawerBottom(paymentBinding.navView.merchantName, paymentBinding.navView.merchantUuid, paymentBinding.navView.merchantShop)
        paymentBinding.appBarMain.tvCurrency.text = MessageUtils.getTransactionCurrencySymbol(
            ApplicationConfigStore().getCurrencyText())
        paymentBinding.appBarMain.amountEdit.setText(R.string.default_amount)
        paymentBinding.appBarMain.toolbar.setNavigationOnClickListener {
            DrawerUtils.drawerOnAndOff(paymentBinding.drawerLayout)
        }
        //Amount entry.
        paymentBinding.appBarMain.amountEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                paymentBinding.appBarMain.doPayment.text = String.format(
                    "%s %s%s",
                    getString(R.string.charge_str),
                    MessageUtils.getTransactionCurrencySymbol(ApplicationConfigStore().getCurrencyText()),
                    s.toString()
                )
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        val listener = View.OnClickListener { v ->
            amount = if (v.tag == getString(R.string.key_back_tag)) {
                if (amount.isNotEmpty()) amount.dropLast(1) else amount
            } else {
                amount + v.tag.toString()
            }

            if (amount.isEmpty()) {
                paymentBinding.appBarMain.amountEdit.setText(R.string.default_amount_display)
                paymentBinding.appBarMain.amountEdit.setTextColor(MaterialColors.getColor(paymentBinding.appBarMain.amountEdit, R.attr.colorOnMuted))
                paymentBinding.appBarMain.tvCurrency.setTextColor(MaterialColors.getColor(paymentBinding.appBarMain.amountEdit, R.attr.colorOnMuted))
                paymentBinding.appBarMain.doPayment.isEnabled = false
            } else {
                paymentBinding.appBarMain.doPayment.isEnabled = true
                val decimalFormat = DecimalFormat(getString(R.string.amount_format_display), DecimalFormatSymbols(
                    Locale.US))
                val amountString = decimalFormat.format(amount.toDouble() / 100.0)
                totalAmount = amountString
                paymentBinding.appBarMain.amountEdit.setText(amountString)
                paymentBinding.appBarMain.amountEdit.setTextColor(MaterialColors.getColor(paymentBinding.appBarMain.amountEdit, R.attr.colorOnSurface))
                paymentBinding.appBarMain.tvCurrency.setTextColor(MaterialColors.getColor(paymentBinding.appBarMain.amountEdit, R.attr.colorOnSurface))
            }
        }
        //Digit pad click event.
        paymentBinding.appBarMain.cashKeypadButton0.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton1.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton2.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton3.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton4.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton5.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton6.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton7.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton8.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton9.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButton00.setOnClickListener(listener)
        paymentBinding.appBarMain.cashKeypadButtonBack.setOnClickListener(listener)
        //Descriptions.
        paymentBinding.appBarMain.addDescription.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                paymentBinding.appBarMain.addDescription.setHintTextColor(Color.TRANSPARENT)
            }
        }
        paymentBinding.appBarMain.addDescription.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                paymentBinding.appBarMain.addDescription.clearFocus()
                if (paymentBinding.appBarMain.addDescription.text.isEmpty()) {
                    paymentBinding.appBarMain.addDescription.setHintTextColor(getColor(R.color.basic_muted))
                }
                true
            } else {
                false
            }
        }
        paymentBinding.appBarMain.addDescription.imeOptions = EditorInfo.IME_ACTION_DONE
        paymentBinding.appBarMain.toolbar.title = ApplicationConfigStore().getMerchantName()

        resultLauncher = registerForActivityResult(HeadlessActivity.contract(CardTransactionActivity::class.java)){
            when (it) {
                is WrappedResult.Success -> {
                    if (it.value.tranType == TranType.SALE) {
                        Log.d(TAG,"SUCCESS:${it.value.tranStatus}-${it.value.paymentMethod.name}")
                        val intent = Intent(this, TransactionResultActivity::class.java)
                        val bundle = Bundle().apply {
                            putBoolean(EXTRA_TRANS_STATUS, true)
                            putString(MHD_EXTRA_TRANS_TYPE,txnType)
                            putString(EXTRA_TRANS_AMOUNT, totalAmount)
                            putString(EXTRA_CARD_BRAND,it.value.paymentMethod.name)
                            putString(EXTRA_TRANS_TC,it.value.tc)
                            putString(MHD_EXTRA_AID,it.value.aid)
                            putString(MHD_EXTRA_TRANS_TVR,it.value.tvr)
                            putString(MHD_EXTRA_TRANS_TSI,it.value.tsi)
                            putString(MHD_EXTRA_AUTH_CODE,it.value.approvalCode)
                            putString(MHD_EXTRA_TRACE_NO,it.value.trace)
                            putString(MHD_EXTRA_REFERENCE_NO,it.value.rrn)
                            putString(MHD_EXTRA_BATCH_NO,it.value.batchNo)
                            putString(MHD_EXTRA_ENTRY_MODE,it.value.entryMode.name)
                            putString(MHD_EXTRA_CREATE_TIME,it.value.createdAt)
                            putString(MHD_EXTRA_MESSAGE_ID,it.value.linkedTranId)
                            putString(EXTRA_PAY_TRANSACTION_ID,it.value.tranId)
                            putString(MHD_EXTRA_PAY_DESCRIPTION,it.value.description)
                        }
                        intent.putExtras(bundle)
                        startActivity(intent)
                        finish()
                    }
                }

                is WrappedResult.Failure -> {
                    Log.d(TAG,"Failed:${it.code}-${it.message}")
                    val intent = Intent(this, TransactionResultActivity::class.java)
                    val bundle = Bundle().apply {
                        putString(EXTRA_TRANS_AMOUNT, totalAmount)
                        putString(MHD_EXTRA_TRANS_TYPE,txnType)
                        putBoolean(EXTRA_TRANS_STATUS, false)
                        putString(MHD_EXTRA_RESPONSE_CODE,it.code.toString())
                        putString(EXTRA_TRANS_ERROR_CODE_MSG,it.message)
                    }
                    intent.putExtras(bundle)
                    startActivity(intent)
                    finish()
                }
            }
        }
        startPaymentAction()
    }

    private fun startPaymentAction() {
        paymentBinding.appBarMain.doPayment.setOnClickListener {
            val transAmount = paymentBinding.appBarMain.amountEdit.text.toString().replace(",","")
            Log.d(TAG,"startPaymentAction profileId:" + ApplicationConfigStore().getProfileId())
            if (transAmount.isNotEmpty()) {
                resultLauncher.launch(
                    PoiRequest.ActionNew(
                        tranType = TranType.SALE,
                        amount = Amount(
                            transAmount.toBigDecimal(),
                            Currency.getInstance(ApplicationConfigStore().getCurrencyText()),
                        ),
                        profileId = ApplicationConfigStore().getProfileId(),
                        preferredAcceptanceTag = "SME",
                        forcePaymentMethod = null,
                        description = paymentBinding.appBarMain.addDescription.text.toString(),
                        posReference = "pos_${UUID.randomUUID()}",
                        forceFetchProfile = true,
                        cvmSignatureMode = CvmSignatureMode.ELECTRONIC_SIGNATURE
                    )
                )
            }
        }
    }
}



