package com.br3ant.ext


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/4/1
 *     desc   :
 *     version: 1.0
 * </pre>
 */

//typealias ActivityResultCallback = (result: ActivityResult) -> Unit

inline fun <reified T : Any> Activity.getValue(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    if (value is T) value else default
}

inline fun <reified T : Any> Activity.getValueNonNull(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    requireNotNull((if (value is T) value else default)) { key }
}

inline fun <reified T : Any> Activity.getDataOrValue(key: String, default: T) = lazy {
    val value = when (default) {
        is Long -> intent?.data?.getQueryParameter(key)?.toLongOrNull()
        is String -> intent?.data?.getQueryParameter(key)
        is Int -> intent?.data?.getQueryParameter(key)?.toIntOrNull()
        is Boolean -> intent?.data?.getQueryParameter(key)?.toBoolean()
        is Float -> intent?.data?.getQueryParameter(key)?.toFloatOrNull()
        else -> throw IllegalArgumentException("This type of data is not support!")
    } ?: intent?.extras?.get(key) ?: default
    return@lazy value as T
}

inline fun <reified T : Activity> Activity.startKtxActivity(
    flag: Int? = null,
    extra: Bundle? = null,
) = startActivity(getIntent<T>(flag, extra))

inline fun <reified T : Activity> Fragment.startKtxActivity(
    flag: Int? = null,
    extra: Bundle? = null,
) = requireActivity().startActivity(requireActivity().getIntent<T>(flag, extra))

inline fun <reified T : Activity> Context.startKtxActivity(
    flag: Int? = null,
    extra: Bundle? = null,
) = startActivity(getIntent<T>(flag, extra))

//inline fun <reified T : Activity> AppCompatActivity.startKtxActivityForResult(
//    flag: Int? = null,
//    extra: Bundle? = null,
//    noinline callback: ActivityResultCallback? = null
//) = getIntent<T>(flag, extra).startKtxActivityForResult(this, callback)
//
//
//inline fun <reified T : Activity> Fragment.startKtxActivityForResult(
//    flag: Int? = null,
//    extra: Bundle? = null,
//    noinline callback: ActivityResultCallback? = null
//) = this.requireActivity().getIntent<T>(flag, extra).startKtxActivityForResult(this, callback)
//
//
//fun Intent.startKtxActivityForResult(activity: AppCompatActivity, callback: ActivityResultCallback? = null) {
//    activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//        callback?.invoke(it)
//    }.launch(this)
//}
//
//fun Intent.startKtxActivityForResult(fragment: Fragment, callback: ActivityResultCallback? = null) {
//    fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//        callback?.invoke(it)
//    }.launch(this)
//}

inline fun <reified T : Context> Context.getIntent(
    flag: Int? = null,
    extra: Bundle? = null,
): Intent = Intent(this, T::class.java).apply {
    flag?.let { addFlags(it) }
    extra?.let { putExtras(extra) }
}