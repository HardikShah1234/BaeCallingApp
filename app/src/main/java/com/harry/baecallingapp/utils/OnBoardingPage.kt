package com.harry.baecallingapp.utils

import androidx.annotation.DrawableRes
import com.harry.baecallingapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.ic_phone_call_svgrepo_com,
        title = "Meet",
        description = "Call your loved once"
    )

    object Second : OnBoardingPage(
        image = R.drawable.ic_screen_share_icon,
        title = "Share",
        description = "Share your screen for entertainment, good work and many more"
    )
}
