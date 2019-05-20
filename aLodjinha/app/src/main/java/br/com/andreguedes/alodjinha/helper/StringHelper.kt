package br.com.andreguedes.alodjinha.helper

import android.os.Build
import android.text.Html
import java.text.NumberFormat
import java.util.*

object StringHelper {

    fun formatCurrencyNew(value: Double?): String {
        return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(value)
    }

    fun formatHTML(description: String?): CharSequence? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
        else Html.fromHtml(description)
    }

}