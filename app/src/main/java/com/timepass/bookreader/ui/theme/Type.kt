package com.timepass.bookreader.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.timepass.bookreader.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
val myfonts = FontFamily(
    Font(R.font.barbarusa_2),
    Font(R.font.barbarusa, FontWeight.Normal),
    Font(R.font.barbarusa_2, weight = FontWeight.Bold)

)
val myFont = FontFamily(Font(R.font.handlerregular))
val myFont2 = FontFamily(Font(R.font.handlerroughregular))
val myFont3 = FontFamily(Font(R.font.handlerstampregular))
val myFont4 = FontFamily(Font(R.font.roundedblock))
val myFont5 = FontFamily(Font(R.font.sanstuybrushregular))
val myFont6 = FontFamily(Font(R.font.sanstuybrushswashregular))
