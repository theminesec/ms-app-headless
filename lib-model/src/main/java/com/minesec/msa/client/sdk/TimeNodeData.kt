package com.minesec.msa.client.sdk

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author eric.song
 * @since 2024/3/5 21:10
 */
data class TimeNodeData(
//    var onlineTxnRequestTime: Long = 0,
//    var deviceInfoRequestTime: Long = 0,
//    var downloadKeyRequestTime: Long = 0,
//    var downloadParamRequestTime: Long = 0,
//    var downloadMerchantLogoRequestTime: Long = 0,
//    var warmupRequestTime: Long = 0,
//    var readCardRequestTime: Long = 0,
    var startingRecordTime: Long = 0
){
    private fun calculateTime(startTime: Long): String {
        val timeDifference = System.currentTimeMillis() - startTime
        val seconds = BigDecimal(timeDifference).divide(BigDecimal(1000))
        return seconds.setScale(3, RoundingMode.HALF_UP).toPlainString()
    }

    fun getCostTime(): String = calculateTime(startingRecordTime)

//    fun getOnlineTransactionCostTime(): String = calculateTime(onlineTxnRequestTime)
//
//    fun getDeviceInfoCostTime(): String = calculateTime(deviceInfoRequestTime)
//
//    fun getDownloadKeyCostTime(): String = calculateTime(downloadKeyRequestTime)
//
//    fun getDownloadParamCostTime(): String = calculateTime(downloadParamRequestTime)
//
//    fun getDownloadMerchantLogoCostTime(): String = calculateTime(downloadMerchantLogoRequestTime)
//
//    fun getWarmupCostTime(): String = calculateTime(warmupRequestTime)
//
//    fun getReadCardCostTime(): String = calculateTime(readCardRequestTime)
}
