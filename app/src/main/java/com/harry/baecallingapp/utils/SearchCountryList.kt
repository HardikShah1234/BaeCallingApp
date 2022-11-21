package com.harry.baecallingapp.utils

import android.content.Context
import com.harry.baecallingapp.utils.ccp.CountryData
import com.harry.baecallingapp.utils.ccp.getCountryName

fun List<CountryData>.searchCountry(key: String,context: Context): MutableList<CountryData> {
    val tempList = mutableListOf<CountryData>()
    this.forEach {
        if (context.resources.getString(getCountryName(it.countryCode)).lowercase().contains(key.lowercase())) {
            tempList.add(it)
        }
    }
    return tempList
}