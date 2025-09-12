package com.kekouke.tasknote.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val TitleH1 = TextStyle(
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
)

val TitleH2 = TextStyle(
    fontSize = 17.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 20.4.sp
)

val Regular15 = TextStyle(
    fontSize = 15.sp,
    lineHeight = 21.sp,
)

val Regular13 = TextStyle(
    fontSize = 13.sp,
    lineHeight = 18.2.sp,
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)