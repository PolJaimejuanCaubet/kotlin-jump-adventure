package com.example.mariobros

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Platform(x: Float, y: Float, width: Float, height: Float) {
    val rect = RectF(x, y, x + width, y + height)
    private val paint = Paint()

    init {
        paint.color = Color.GREEN
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }
}