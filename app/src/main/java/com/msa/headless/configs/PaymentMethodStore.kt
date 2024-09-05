package com.msa.headless.configs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.msa.headless.APP

/**
 * @author eric.song
 * @since 2024/6/19 16:33
 */
class PaymentMethodStore {
    private val sp: SharedPreferences = APP.instance.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    fun install(defaults: List<Model>) {
        for (model in defaults) {
            save(model)
        }
        sp.edit().putBoolean(INSTALL_KEY, true).apply()
    }

    fun removeInstallKey() {
        sp.edit().remove(INSTALL_KEY).apply()
    }

    fun save(paymentMethodName: String, paymentMethodCode: String) {
        save(Model(paymentMethodName, paymentMethodCode))
    }

    fun save(model: Model) {
        sp.edit().putString(model.paymentMethodName, Gson().toJson(model)).apply()
    }

    fun find(paymentMethodName: String): Model? {
        val jsonStr = sp.getString(paymentMethodName, null)
        return jsonStr?.let { Gson().fromJson(it, Model::class.java) }
    }

    fun delete(paymentMethodName: String) {
        sp.edit().remove(paymentMethodName).apply()
    }

    fun all(): List<Model> {
        val result = mutableListOf<Model>()
        val all = sp.all.filterKeys { it != INSTALL_KEY }
        for ((_, value) in all) {
            val jsonStr = value as String
            result.add(Gson().fromJson(jsonStr, Model::class.java))
        }
        return result
    }

    data class Model(val paymentMethodName: String, val paymentMethodCode: String)

    companion object {
        private const val SP_NAME = "app_payment_method_settings"
        private const val INSTALL_KEY = "__install__"
    }
}