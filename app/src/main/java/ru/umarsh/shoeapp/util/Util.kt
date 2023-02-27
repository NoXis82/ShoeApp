package ru.umarsh.shoeapp.util

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.ui.graphics.Color

object Util {
    fun randomColor(): Color {
        return listOf(
            0xFFE57373.toInt(),
            0xFFF06292.toInt(),
            0xFFBA68C8.toInt(),
            0xFF9575CD.toInt(),
            0xFF7986CB.toInt(),
            0xFF64B5F6.toInt(),
            0xFF4FC3F7.toInt(),
            0xFF4DD0E1.toInt(),
            0xFF4DB6AC.toInt(),
            0xFF81C784.toInt(),
            0xFFAED581.toInt(),
            0xFFDCE775.toInt(),
            0xFFFFD54F.toInt(),
            0xFFFFB74D.toInt(),
            0xFFA1887F.toInt(),
            0xFF90A4AE.toInt()
        ).map {
            Color(it)
        }.random()
    }

    fun lerp(start: Float, stop: Float, fraction: Float): Float =
        (1 - fraction) * start + fraction * stop


    val EaseOutBounce: Easing = Easing { fraction ->
        val n1 = 7.5625f
        val d1 = 2.75f
        var newFraction = fraction

        return@Easing if (newFraction < 1f / d1) {
            n1 * newFraction * newFraction
        } else if (newFraction < 2f / d1) {
            newFraction -= 1.5f / d1
            n1 * newFraction * newFraction + 0.75f
        } else if (newFraction < 2.5f / d1) {
            newFraction -= 2.25f / d1
            n1 * newFraction * newFraction + 0.9375f
        } else {
            newFraction -= 2.625f / d1
            n1 * newFraction * newFraction + 0.984375f
        }
    }
    val EaseOutQuart = CubicBezierEasing(0.25f, 1f, 0.5f, 1f)

}