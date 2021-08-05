package com.bennet.wallet.preferences

import android.content.Context
import androidx.preference.PreferenceManager
import com.bennet.wallet.R
import com.bennet.wallet.preferences.AppPreferenceManager
import com.bennet.wallet.activities.cards.CardActivity
import java.util.*

/**
 * This static class is used for reading the app preferences
 */
object AppPreferenceManager {
    @JvmStatic
    private fun getBackConfirmStringSet(context: Context): Set<String>? {
        val defaultValueArray =
            context.resources.getStringArray(R.array.preferences_back_confirm_default)
        val defaultValue: Set<String> = HashSet(listOf(*defaultValueArray))
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getStringSet(context.getString(R.string.preferences_back_confirm_key), defaultValue)
    }

    @JvmStatic
    private fun getAppFunctionsStringSet(context: Context): Set<String>? {
        val defaultValueArray =
            context.resources.getStringArray(R.array.preferences_app_functions_entry_values)
        val defaultValue: Set<String> = HashSet(listOf(*defaultValueArray))
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getStringSet(context.getString(R.string.preferences_app_functions_key), defaultValue)
    }

    /**
     * @param context Context
     * @return Returns the string value of the app theme in preferences, null if not found in preferences
     */
    @JvmStatic
    fun getAppDarkMode(context: Context): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(context.getString(R.string.preferences_theme_key), null)
    }

    @JvmStatic
    fun isBackConfirmNewCardOrPassword(context: Context): Boolean {
        val stringSet = getBackConfirmStringSet(context)
        return stringSet!!.contains(context.getString(R.string.preferences_back_confirm_entry_values_new_card_or_password))
    }

    @JvmStatic
    fun isBackConfirmCrop(context: Context): Boolean {
        val stringSet = getBackConfirmStringSet(context)
        return stringSet!!.contains(context.getString(R.string.preferences_back_confirm_entry_values_crop))
    }

    @JvmStatic
    fun isBackConfirmEditCardOrPassword(context: Context): Boolean {
        val stringSet = getBackConfirmStringSet(context)
        return stringSet!!.contains(context.getString(R.string.preferences_back_confirm_entry_values_edit_card_or_password))
    }

    @JvmStatic
    fun getDefaultCardCodeType(context: Context): Int {
        val defaultTypeString = PreferenceManager.getDefaultSharedPreferences(context).getString(
            context.getString(R.string.preferences_default_values_barcode_key),
            context.getString(R.string.preferences_default_values_barcode_default)
        )
        return CardActivity.codeTypeStringToInt(context, defaultTypeString)
    }

    @JvmStatic
    fun getDefaultWithText(context: Context): Boolean {
        val defaultTextString = PreferenceManager.getDefaultSharedPreferences(context).getString(
            context.getString(R.string.preferences_default_values_code_text_key),
            context.getString(R.string.preferences_default_values_code_text_default)
        )
        return defaultTextString == context.getString(R.string.preferences_default_values_code_text_entry_values_with_text)
    }

    @JvmStatic
    fun isAppFunctionCards(context: Context): Boolean {
        val stringSet = getAppFunctionsStringSet(context)
        return stringSet!!.contains(context.getString(R.string.preferences_app_functions_entry_values_cards))
    }

    @JvmStatic
    fun isAppFunctionPasswords(context: Context): Boolean {
        val stringSet = getAppFunctionsStringSet(context)
        return stringSet!!.contains(context.getString(R.string.preferences_app_functions_entry_values_passwords))
    }

    /**
     * Checks in the user preferences, whether the user wants to receive detailed error messages or not
     * @param context Context
     * @return Returns true when detailed error messages are turned on, otherwise false
     */
    @JvmStatic
    fun isDetailedErrors(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
            context.getString(R.string.preferences_detailed_errors_key),
            context.resources.getBoolean(R.bool.preferences_detailed_errors_default)
        )
    }

    @JvmStatic
    fun isLengthHiddenInSecretFields(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
            context.getString(R.string.preferences_hide_length_secret_field_key),
            context.resources.getBoolean(R.bool.preferences_hide_length_secret_field_default)
        )
    }

    @JvmStatic
    fun isMonospaceInSecretFields(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
            context.getString(R.string.preferences_secret_field_monospace_key),
            context.resources.getBoolean(R.bool.preferences_secret_field_monospace_default)
        )
    }
}