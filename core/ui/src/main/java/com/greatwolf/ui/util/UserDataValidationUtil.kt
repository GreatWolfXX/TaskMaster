package com.greatwolf.ui.util

import android.util.Patterns

fun String.isEmailValid(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isRepeatPasswordValid(repeatPassword: String): Boolean {
    return this == repeatPassword
}