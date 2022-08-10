package com.myapps.coinksimpleregistersimulator.domain.utils

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.myapps.coinksimpleregistersimulator.domain.constans.STRING_DATE_REGEX_PATTERN
import com.myapps.coinksimpleregistersimulator.domain.constans.STRING_EMPTY
import com.myapps.coinksimpleregistersimulator.domain.constans.STRING_SPACE_REGEX_PATTERN

fun Fragment.navigate(resId: NavDirections, bundle: Bundle?) {
    view?.findNavController()?.navigate(resId)
}

fun Fragment.navigateWithDirections(navDirection: NavDirections) {
    view?.findNavController()?.navigate(navDirection)
}

fun String.isEmailNoValid() : Boolean {
    return this.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(this).matches().not()
}

fun String.isDateFormatValid() : Boolean {
    return this.matches(Regex(STRING_DATE_REGEX_PATTERN))
}

fun String.deleteSpaces() : String {
    return this.replace(STRING_SPACE_REGEX_PATTERN.toRegex(), STRING_EMPTY)
}

fun Fragment.showSnackBar(message: String) {
    activity?.let {
        val view: View = it.findViewById(android.R.id.content)
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}

fun Toolbar.hideToolbar() {
    this.visibility = View.GONE
}

fun Toolbar.showToolbar() {
    this.visibility = View.VISIBLE
}

fun ProgressBar.hideProgressBar() {
    this.visibility = View.INVISIBLE
}

fun ProgressBar.showProgressBar() {
    this.visibility = View.VISIBLE
}