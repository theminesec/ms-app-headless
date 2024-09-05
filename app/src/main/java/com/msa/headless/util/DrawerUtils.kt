package com.msa.headless.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.minesec.msa.client.sdk.SoftPOSTransType
import com.msa.headless.R
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.ui.MHD_EXTRA_TRANS_TYPE
import com.msa.headless.ui.view.ui.PaymentActivity
import com.msa.headless.ui.view.ui.SettingsActivity

/**
 * @author eric.song
 * @since 2024/7/31 17:30
 */
object DrawerUtils {
    fun drawerOnAndOff(drawerLayout: DrawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun selectNavigationDrawerItem(
        context: Context,
        drawerLayout: DrawerLayout,
        item: MenuItem,
        txnType: String?
    ) {
        when (item.itemId) {
            R.id.nav_history -> {
                //History
//                if (context !is HistoryActivity) {
//                    context.startActivity(Intent(context, HistoryActivity::class.java))
//                }
            }
            R.id.nav_sale -> {
                //Sale
                if (context !is PaymentActivity || SoftPOSTransType.TRANS_PRE_AUTH == txnType) {
                    val intent = Intent(context, PaymentActivity::class.java).apply {
                        putExtra(MHD_EXTRA_TRANS_TYPE, SoftPOSTransType.TRANS_SALE)
                    }
                    context.startActivity(intent)
                }
            }
            R.id.nav_settlement -> {
                //settlement
//                context.startActivity(Intent(context, SettlementActivity::class.java))
            }
            R.id.nav_support -> {
                //support
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ApplicationConfigStore().getHelpSupportUrl()))
                context.startActivity(intent)
            }
            R.id.nav_settings -> {
                //settings
                if (context !is SettingsActivity) {
                    context.startActivity(Intent(context, SettingsActivity::class.java))
                }
            }
            R.id.nav_auth -> {
                if (context !is PaymentActivity || SoftPOSTransType.TRANS_SALE == txnType) {
                    val intent = Intent(context, PaymentActivity::class.java).apply {
                        putExtra(MHD_EXTRA_TRANS_TYPE, SoftPOSTransType.TRANS_PRE_AUTH)
                    }
                    context.startActivity(intent)
                }
            }
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun clearNavigationItemCheck(
        navigationView1: NavigationView,
        navigationView2: NavigationView
    ) {
        navigationView1.menu.findItem(R.id.nav_settlement)?.isChecked = false
        navigationView2.menu.findItem(R.id.nav_support)?.isChecked = false
    }

    fun setNavigationDrawerBottom(shop: TextView, merchantUUID: TextView, shopIcon: ImageView) {
        val applicationConfigStore = ApplicationConfigStore()
        shop.text = applicationConfigStore.getMerchantName()
        merchantUUID.text = applicationConfigStore.getMerchantUUID()
        // todo - remote image from shop logo?
        // shopIcon.setImageResource(R.drawable.ico_biz_merchant)
    }

    fun checkMenuOptions(navigationView: NavigationView) {
        val applicationConfigStore = ApplicationConfigStore()
        val menu = navigationView.menu
        val navSettlementItem = menu.findItem(R.id.nav_settlement)
        navSettlementItem.isVisible = applicationConfigStore.getEnableSettlement()
        val navAuthItem = menu.findItem(R.id.nav_auth)
        navAuthItem.isVisible = applicationConfigStore.isEnableAuthTransaction()

        val settlementItem = menu.findItem(R.id.nav_settlement)
        settlementItem.isVisible = false
        val historyItem = menu.findItem(R.id.nav_history)
        historyItem.isVisible = false
    }
}