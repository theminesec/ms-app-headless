package com.msa.headless.ui.view.ui


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.theminesec.sdk.headless.HeadlessActivity
import com.theminesec.sdk.headless.model.transaction.Amount
import com.theminesec.sdk.headless.model.transaction.PaymentMethod
import com.theminesec.sdk.headless.ui.ThemeProvider
import com.theminesec.sdk.headless.ui.UiProvider
import com.theminesec.sdk.headless.ui.UiState

class CardTransactionActivity : HeadlessActivity() {
    override fun provideTheme(): ThemeProvider = ClientHeadlessThemeProvider()
    override fun provideUi(): UiProvider = ClientUiProvider()
}


class ClientHeadlessThemeProvider : ThemeProvider() {
    override fun provideColors(darkTheme: Boolean): HeadlessColors {
        return if (darkTheme) {
            HeadlessColorsDark().copy(
                primary = Color(0xFFD5A23B).toArgb()
            )
        } else {
            HeadlessColorsLight().copy(
                primary = Color(0xFFFFC145).toArgb()
            )
        }
    }
}

class ClientUiProvider : UiProvider() {
    @Composable
    override fun AmountDisplay(amount: Amount, description: String?) {
        Box(
            modifier = Modifier.border(1.dp, Color.Red),
            contentAlignment = Alignment.Center
        ) {
            super.AmountDisplay(amount, description)
        }
    }

    @Composable
    override fun AcceptanceMarkDisplay(supportedPayments: List<PaymentMethod>, showWallet: Boolean) {
        Box(
            modifier = Modifier.border(1.dp, Color.Red),
            contentAlignment = Alignment.Center
        ) {
            super.AcceptanceMarkDisplay(supportedPayments, true)
        }
    }

    @Composable
    override fun AwaitCardIndicator() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, Color.Red),
        ) {
            super.AwaitCardIndicator()
        }
    }

    @Composable
    override fun ProgressIndicator() {
        Box(
            modifier = Modifier.border(1.dp, Color.Red),
            contentAlignment = Alignment.Center
        ) {
            super.ProgressIndicator()
        }
    }

    @Composable
    override fun UiStateDisplay(modifier: Modifier, uiState: UiState, countdownSec: Int) {
        Box(
            modifier = Modifier.border(1.dp, Color.Red),
            contentAlignment = Alignment.Center
        ) {
            super.UiStateDisplay(modifier, uiState, countdownSec)
        }
    }
}

