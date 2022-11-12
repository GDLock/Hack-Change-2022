package com.otkritie.hackaton.data.local.preference

import android.content.SharedPreferences
import javax.inject.Singleton

@Singleton
class AuthPreferenceImpl(
    private val preference: SharedPreferences
) : AuthPreference {

    override var token: String?
        get() = preference.getString(TOKEN_KEY, null)
        set(value) { setString(TOKEN_KEY, value) }

    private fun setString(key: String, value: String?) {
        preference.edit().putString(key, value).apply()
    }

    private companion object {
        const val TOKEN_KEY = "token"
    }
}
