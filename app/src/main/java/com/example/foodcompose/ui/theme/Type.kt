package com.example.foodcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.foodcompose.R


val SFProRounded = FontFamily(
    Font(R.font.sf_pro_rounded_heavy),
    Font(R.font.sf_pro_rounded_regular),
    Font(R.font.sf_pro_rounded_bold),
    Font(R.font.sf_pro_rounded_semibold),
)

val SFProText = FontFamily(
    Font(R.font.sf_pro_text_regular),
    Font(R.font.sf_pro_text_semibold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        color = White,
        fontFamily = SFProRounded,
        fontSize = 65.sp,
        fontWeight = FontWeight.W800
    ),
    h2 = TextStyle(
        color = Black,
        fontFamily = SFProRounded,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold
    ),
    h3 = TextStyle(
        color = Black,
        fontFamily = SFProText,
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold
    ),
    h4 = TextStyle(
        color = Black,
        fontFamily = SFProText,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    subtitle1 = TextStyle(
        color = Secondary,
        fontFamily = SFProText,
        fontSize = 18.sp,
        fontWeight = FontWeight.W400
    ),
    subtitle2 = TextStyle(
        color = Secondary,
        fontFamily = SFProText,
        fontSize = 15.sp,
        fontWeight = FontWeight.W400
    ),
    body1 = TextStyle(
        color = Black,
        fontFamily = SFProText,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    body2 = TextStyle(
        color = Secondary,
        fontFamily = SFProText,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold
    ),
    button = TextStyle(
        color = Color.Unspecified,
        fontSize = 17.sp,
        fontWeight = FontWeight.W600
    )
)