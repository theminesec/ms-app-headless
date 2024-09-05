package com.msa.headless.ui.view.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.msa.headless.R
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.databinding.ActivitySettingsBinding
import com.msa.headless.model.SettingsNavigator
import com.msa.headless.ui.LoadingDialog
import com.msa.headless.util.DrawerUtils
import com.msa.headless.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch

class SettingsActivity : BaseActivity(), SettingsNavigator {

    private lateinit var settingsBinding: ActivitySettingsBinding
    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(settingsBinding.root)

        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        viewModel.onActivityCreated(this)

        val applicationConfigStore = ApplicationConfigStore(this)

        // Switch off pre-auth transactions.
        settingsBinding.appBarSetting.switchPreauth.isChecked = applicationConfigStore.isEnableAuthTransaction()
        settingsBinding.appBarSetting.switchPreauth.setOnCheckedChangeListener { _, isChecked ->
            updateAuthMenuItem(isChecked)
            applicationConfigStore.setEnableAuthTransaction(isChecked)
        }

        settingsBinding.appBarSetting.tvMchName.text = applicationConfigStore.getMerchantName()
        settingsBinding.appBarSetting.tvDeviceId.text = Build.MODEL
        settingsBinding.appBarSetting.tvMid.text = applicationConfigStore.getMerchantId()
        settingsBinding.appBarSetting.tvSoftwarePkg.text = packageName
        settingsBinding.appBarSetting.tvAppVersion.text = viewModel.getFormatApplicationVersion()
        settingsBinding.appBarSetting.tvHostVersion.text = applicationConfigStore.getBackendSoftwareVersion()
        settingsBinding.appBarSetting.tvProfileId.text = applicationConfigStore.getProfileId()
        settingsBinding.appBarSetting.tvSdkId.text = applicationConfigStore.getSdkId()
        settingsBinding.appBarSetting.tvPaymentSoftwarePkg.setText(R.string.payment_app_software)
        settingsBinding.appBarSetting.tvPaymentAppVersion.setText(R.string.payment_app_ver)

        settingsBinding.appBarSetting.toolbar.setNavigationOnClickListener {
            DrawerUtils.drawerOnAndOff(settingsBinding.drawerLayout)
        }

        DrawerUtils.checkMenuOptions(settingsBinding.navView.navigationFirstView)
        settingsBinding.navView.navigationFirstView.setNavigationItemSelectedListener { item ->
            DrawerUtils.selectNavigationDrawerItem(this, settingsBinding.drawerLayout, item, "")
            true
        }

        settingsBinding.navView.navigationSecondView.setNavigationItemSelectedListener { item ->
            DrawerUtils.selectNavigationDrawerItem(this, settingsBinding.drawerLayout, item, "")
            true
        }

        settingsBinding.appBarSetting.btnLogOut.setOnClickListener {
            lifecycleScope.launch {
                viewModel.logout()
            }
        }

        DrawerUtils.setNavigationDrawerBottom(
            settingsBinding.navView.merchantName,
            settingsBinding.navView.merchantUuid,
            settingsBinding.navView.merchantShop
        )
    }

    private fun updateAuthMenuItem(isEnabled: Boolean) {
        val menuAuthItem = settingsBinding.navView.navigationFirstView.menu.findItem(R.id.nav_auth)
        menuAuthItem?.isVisible = isEnabled
    }

    override fun getApplicationVersion(): String {
        return try {
            val pInfo = packageManager.getPackageInfo(packageName, 0)
            pInfo.versionName
        } catch (e: Exception) {
            e.printStackTrace()
            ""
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

    override fun displayError(resId: Int, errorMessage: String) {
        alertOnly(String.format(getString(resId), errorMessage))
    }

    override fun onLogoutSuccess() {
        hideLoadingProgress()
        ApplicationConfigStore().setToken("")
        val intent = Intent(this, ActivationActivity::class.java)
        startActivity(intent)
        finish()
    }

}