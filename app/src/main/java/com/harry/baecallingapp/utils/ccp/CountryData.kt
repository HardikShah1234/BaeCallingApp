package com.harry.baecallingapp.utils.ccp

import com.harry.baecallingapp.R
import java.util.*


data class CountryData(
    private var cCodes: String,
    val countryPhoneCode: String = "+90",
    val cNames: String = "tr",
    val flagResID: Int = R.drawable.tr
) {
    val countryCode = cCodes.lowercase(Locale.getDefault())
}