package com.artsam.presentation.compose.ui.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artsam.presentation.R
import com.artsam.presentation.compose.ui.theme.Purple80

@OptIn(ExperimentalTextApi::class)
@Composable
fun Toolbar() {
    val GradientColors = listOf(Color.Magenta, Color.Blue, Purple80)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(start = 20.dp),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.W600,
            fontFamily = FontFamily.Serif,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = GradientColors
                )
            ),
            text = "Mukunda Mohini devi dasi"
        )
        Image(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, end = 16.dp)
                .size(64.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ghovardhan),
            contentDescription = "Avatar",
            contentScale = ContentScale.Inside
        )
    }
    Divider(thickness = 1.dp)
}
