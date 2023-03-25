package com.ivanshalimov.stringutils

import android.graphics.Color
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.security.MessageDigest
import java.util.regex.Pattern

object StringUtils {

    val String.md5: String
        get() {
            val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }

    val String.sha1: String
        get() {
            val bytes = MessageDigest.getInstance("SHA-1").digest(this.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }

    fun String.isEmailValid(): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }
    fun String.formatPhoneNumber(region: String): String? {
        val phoneNumberKit = PhoneNumberUtil.getInstance()
        val number = phoneNumberKit.parse(this, region)
        if (!phoneNumberKit.isValidNumber(number))
            return null

        return phoneNumberKit.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
    }

    val String.containsLatinLetter: Boolean
        get() = matches(Regex(".*[A-Za-z].*"))

    val String.containsDigit: Boolean
        get() = matches(Regex(".*[0-9].*"))

    val String.isAlphanumeric: Boolean
        get() = matches(Regex("[A-Za-z0-9]*"))

    val String.hasLettersAndDigits: Boolean
        get() = containsLatinLetter && containsDigit

    val String.isIntegerNumber: Boolean
        get() = toIntOrNull() != null

    val String.toDecimalNumber: Boolean
        get() = toDoubleOrNull() != null

    val String.asColor: Int?
        get() = try {
            Color.parseColor(this)
        } catch (e: java.lang.IllegalArgumentException) {
            null
        }
}