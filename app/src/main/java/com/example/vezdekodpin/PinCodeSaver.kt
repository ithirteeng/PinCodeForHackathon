package com.example.vezdekodpin

import android.content.Context
import android.content.SharedPreferences

class PinCodeSaver {
    companion object {
        const val STORAGE_NAME = "storage"
    }

    private var pin: SharedPreferences? = null
    private var pinEditor: SharedPreferences.Editor? = null
    private var context: Context? = null

    private fun init(myContext: Context) {
        context = myContext;
        pin = context?.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        pinEditor = pin?.edit()
    }

    fun makePinSaving(myContext: Context, key: String, pinCode: String) {
        init(myContext)
        pinEditor?.putString(key, pinCode)
        pinEditor?.commit()
    }

    fun takeSavedPin(myContext: Context, key: String) : String? {
        init(myContext)
        return pin?.getString(key, null)
    }

    fun clearPinCode(myContext: Context) {
        init(myContext)
        pinEditor?.clear()
    }
}