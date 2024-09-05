package com.msa.headless.model

import androidx.annotation.StringRes

/**
 * @author eric.song
 * @since 2024/7/31 15:22
 */
interface Navigator {
    fun alert(@StringRes resId: Int)

    fun alert(message: String)

    fun alert(tr: Throwable)

    fun alertOnly(message: String)

    fun alertWithListener(message: String, runnable: Runnable)

    fun getString(@StringRes resId: Int, vararg args: Any): String

    fun getString(@StringRes resId: Int): String
}