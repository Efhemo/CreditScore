package com.efhem.creditscore.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.efhem.creditscore.ui.theme.CreditScoreTheme
import com.efhem.creditscore.ui.theme.Orange
import com.efhem.creditscore.ui.theme.OrangeYellow2
import com.efhem.creditscore.ui.theme.TextOrange


@Composable
fun DonutView(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color = Color.Black.copy(alpha = 0.4f),
    backgroundIndicatorStrokeWidth: Float = 4f,
    progressIndicatorColors: List<Color> = listOf(Orange, OrangeYellow2),
    progressIndicatorStrokeWidth: Float = 12f,
    bigTextColor: Color = TextOrange,
    boldTextColor: Color = Color.Black.copy(alpha = 0.7f),
    bigTextFontSize: TextUnit = MaterialTheme.typography.h3.fontSize,
    textColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
    smallTextFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    onClick: (() -> Unit)? = null
) {


    var allowedIndicatorValue by remember {
        mutableStateOf(maxIndicatorValue)
    }

    allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue) {
        indicatorValue
    } else maxIndicatorValue

    var animatedIndicatorValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage = (animatedIndicatorValue / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (3.6 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1000)
    )

    val animatedBigTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue == 0) textColor else bigTextColor,
        animationSpec = tween(1000)
    )

    val animatedTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue == 0) textColor else boldTextColor,
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .clickable {
                if (onClick != null) {
                    onClick()
                }
            }
            .drawBehind {
                val componentSize = size / 1.25f
                val componentSizeInner = size / 1.37f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                )
                progressIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSizeInner,
                    indicatorColors = progressIndicatorColors,
                    indicatorStrokeWidth = progressIndicatorStrokeWidth
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EmbeddedElement(
            value = receivedValue,
            smallTextFontSize = smallTextFontSize,
            bigTextFontSize = bigTextFontSize,
            maxValue = maxIndicatorValue,
            textColor = animatedTextColor,
            bigTextColor = animatedBigTextColor
        )

    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
) {

    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 0f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        ),
    )
}


fun DrawScope.progressIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColors: List<Color>,
    indicatorStrokeWidth: Float,
) {

    drawArc(
        size = componentSize,
        startAngle = -90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        ),
        brush = Brush.verticalGradient(
            colors = indicatorColors
        )
    )
}


@Composable
fun EmbeddedElement(
    value: Int,
    smallTextFontSize: TextUnit,
    bigTextFontSize: TextUnit,
    maxValue: Int,
    textColor: Color,
    bigTextColor: Color
) {

    Text(
        text = "Your credit score is",
        color = textColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )

    Text(
        text = "$value",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
    )

    Text(
        text = "out of $maxValue",
        color = textColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )

}


@Preview
@Composable
fun CustomComponentPreview() {
    DonutView()
}