@file:Suppress("UNCHECKED_CAST")

package com.yahorb.mvvm.extension

import androidx.fragment.app.FragmentActivity

/**
 * Retrieve property from intent
 */
fun <T : Any> FragmentActivity.argument(key: String) =
    lazy { intent.extras?.get(key) as T }
