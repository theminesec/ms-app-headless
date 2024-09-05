package com.msa.headless.errorcode


/**
 * @author eric.song
 * @since 2024/6/25 14:50
 */
object ServiceErrorUtils {
//
//    fun handleErrorCode(errorCode: Int) {
//        val serviceError = errorCode.getErrorCode()
//        val currentActivity: Activity = ComponentApplication.getCurrentActivity() ?: return
//        when (serviceError.serviceErrorCode.actionLevel) {
//            ServiceErrorActionLevel.REACTIVE_LEVEL -> {
//                val intent = Intent(currentActivity, ActivationActivity::class.java)
//                currentActivity.startActivity(intent)
//            }
//
//            ServiceErrorActionLevel.INFO_ONLY -> {
//                if (currentActivity is TransResultActivity) {
//                    val intent = Intent(currentActivity, PaymentActivity::class.java)
//                    currentActivity.startActivity(intent)
//                } else {
//                    val builder = MaterialAlertDialogBuilder(ComponentApplication.getCurrentActivity())
//                    builder.setMessage(errorCode.getErrorCode().errorDescription)
//                    builder.setCancelable(false)
//                    builder.setPositiveButton(R.string.btn_dismiss) { dialogInterface, _ ->
//                        dialogInterface.dismiss()
//                    }
//                    builder.show()
//                }
//            }
//
//            ServiceErrorActionLevel.ACTION_RETRY_LEVEL -> {
//                if (currentActivity is TransResultActivity) {
//                    val intent = Intent(currentActivity, PaymentActivity::class.java)
//                    currentActivity.startActivity(intent)
//                } else {
//                    val builder = MaterialAlertDialogBuilder(ComponentApplication.getCurrentActivity())
//                    builder.setMessage(R.string.error_action_retry)
//                    builder.setCancelable(false)
//                    builder.setPositiveButton(R.string.btn_dismiss) { dialogInterface, _ ->
//                        dialogInterface.dismiss()
//                    }
//                    builder.show()
//                }
//            }
//
//            ServiceErrorActionLevel.RESET_LEVEL -> {
//                val intent = Intent(currentActivity, MainActivity::class.java)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                currentActivity.startActivity(intent)
//            }
//
//            ServiceErrorActionLevel.ACTION_RETRY_WITH_PIN_LEVEL -> {
//                //Implemented in transaction flow.
//                if (currentActivity is TransResultActivity) {
//                    val intent = Intent(currentActivity, PaymentActivity::class.java)
//                    currentActivity.startActivity(intent)
//                }
//            }
//
//        }
//    }
//
//    fun getErrorDescription(errorCode: Int): String {
//        return errorCode.getErrorCode().errorDescription
//    }
//
//    fun getActionNameByErrorCode(errorCode: Int): String {
//        val serviceError = errorCode.getErrorCode()
//        when (serviceError.serviceErrorCode.actionLevel) {
//            ServiceErrorActionLevel.REACTIVE_LEVEL -> {
//                return getString(R.string.re_activation)
//            }
//
//            ServiceErrorActionLevel.ACTION_RETRY_WITH_PIN_LEVEL -> {
//                return getString(R.string.service_error_retry)
//            }
//
//            ServiceErrorActionLevel.RESET_LEVEL -> {
//                return getString(R.string.service_error_restart)
//            }
//
//            ServiceErrorActionLevel.ACTION_RETRY_LEVEL -> {
//                return getString(R.string.service_error_retry)
//            }
//
//            else -> {}
//        }
//        return ""
//    }
}