package com.greatwolf.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.greatwolf.ui.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Inter")

val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)

private const val lineHeight120 = 1.2f
private const val lineHeight140 = 1.4f
private const val lineHeight150 = 1.5f
private const val lineHeight155 = 1.55f
private const val lineHeight160 = 1.60f
private val heading1FontSize = 48.sp
private val heading2FontSize = 40.sp
private val heading3FontSize = 32.sp
private val heading4FontSize = 24.sp
private val heading5FontSize = 20.sp
private val heading6FontSize = 18.sp
private val bodyLargeFontSize = 18.sp
private val bodyMediumFontSize = 16.sp
private val bodySmallFontSize = 14.sp
private val bodyXSmallFontSize = 12.sp

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = heading1FontSize,
        lineHeight = heading1FontSize * lineHeight120
    ),
    displayMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = heading2FontSize,
        lineHeight = heading2FontSize * lineHeight120
    ),
    displaySmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = heading3FontSize,
        lineHeight = heading3FontSize * lineHeight140
    ),
    headlineLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = heading4FontSize,
        lineHeight = heading4FontSize * lineHeight150
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = heading5FontSize,
        lineHeight = heading5FontSize * lineHeight140
    ),
    headlineSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = heading6FontSize,
        lineHeight = heading6FontSize * lineHeight140
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = bodyLargeFontSize,
        lineHeight = bodyLargeFontSize * lineHeight155
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = bodyLargeFontSize,
        lineHeight = bodyLargeFontSize * lineHeight155
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = bodyLargeFontSize,
        lineHeight = bodyLargeFontSize * lineHeight155
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = bodyMediumFontSize,
        lineHeight = bodyMediumFontSize * lineHeight155
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = bodyMediumFontSize,
        lineHeight = bodyMediumFontSize * lineHeight160
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = bodyMediumFontSize,
        lineHeight = bodyMediumFontSize * lineHeight160
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = bodySmallFontSize,
        lineHeight = bodySmallFontSize * lineHeight155
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = bodySmallFontSize,
        lineHeight = bodySmallFontSize * lineHeight155
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = bodySmallFontSize,
        lineHeight = bodySmallFontSize * lineHeight155
    )
)

val BodyXSmallTextStyleSemiBold = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = bodyXSmallFontSize,
    lineHeight = bodyXSmallFontSize * lineHeight155
)

val BodyXSmallTextStyleMedium = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = bodyXSmallFontSize,
    lineHeight = bodyXSmallFontSize * lineHeight155
)

val BodyXSmallTextStyleNormal = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = bodyXSmallFontSize,
    lineHeight = bodyXSmallFontSize * lineHeight155
)

val BodySmallStrikethroughTextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = bodySmallFontSize,
    lineHeight = bodySmallFontSize * lineHeight155,
    textDecoration = TextDecoration.LineThrough
)

val NoteTextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = bodySmallFontSize,
    lineHeight = bodySmallFontSize * lineHeight155,
    textDecoration = TextDecoration.LineThrough
)