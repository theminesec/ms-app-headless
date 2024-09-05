package com.msa.headless.model

/**
 * @author eric.song
 * @since 2024/6/18 22:33
 */
sealed class StartingNavigator {

    data object ToActivation : StartingNavigator()

    data object ToPayment : StartingNavigator()

    data class ToError(val message: String) : StartingNavigator()
}