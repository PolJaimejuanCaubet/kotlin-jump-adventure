package com.example.mariobros

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Mario(var x: Float, var y: Float) {
    val width = 50f
    val height = 50f
    var isJumping = false
    private var velocityY = 0f
    private val gravity = 0.6f
    private val jumpStrength = -15f
    val rect = RectF(x, y, x + width, y + height)
    private val paint = Paint()

    init {
        paint.color = Color.RED
    }

    fun update() {
        if (isJumping) {
            velocityY += gravity
            y += velocityY
        }
        updateRect()
    }

    private fun updateRect() {
        rect.set(x, y, x + width, y + height)
    }

    fun jump() {
        if (!isJumping) {
            isJumping = true
            velocityY = jumpStrength
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }
}