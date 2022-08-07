package com.myapps.coinksimpleregistersimulator.domain.utils

import android.icu.number.FormattedNumber
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.Format
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

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
    return this.matches(Regex("^\\d{2}/\\d{2}/\\d{4}$"))
}

fun String.deleteSpaces() : String {
    return this.replace("\\s".toRegex(), "")
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