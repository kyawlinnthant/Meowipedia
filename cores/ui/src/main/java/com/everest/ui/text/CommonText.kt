package com.everest.ui.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.everest.theme.dimen

@Composable
fun CommonText(
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    textColor: Color = Color.Black,
    fontWeight: FontWeight? = null,
    fontStyle: FontStyle? = null,
    fontSize: Int = MaterialTheme.dimen.normalTextSize
) {
    Text(
        text = text,
        maxLines = maxLines,
        style = TextStyle(
            color = textColor,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSize = fontSize.sp
        )
    )
}
