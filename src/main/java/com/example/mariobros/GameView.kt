package com.example.mariobros

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), Runnable {
    private val thread: Thread
    private var isPlaying = false
    private val paint: Paint = Paint()
    private lateinit var canvas: Canvas
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var mario: Mario
    private val platforms: MutableList<Platform> = mutableListOf()

    init {
        surfaceHolder = holder
        thread = Thread(this)
        mario = Mario(100f, 100f)
        platforms.add(Platform(0f, 700f, width.toFloat(), 100f))
    }

    override fun run() {
        while (isPlaying) {
            update()
            draw()
            control()
        }
    }

    private fun update() {
        mario.update()
        checkCollisions()
    }

    private fun checkCollisions() {
        for (platform in platforms) {
            if (mario.rect.intersect(platform.rect)) {
                mario.y = platform.y - mario.height
                mario.isJumping = false
                break
            }
        }
    }

    private fun draw() {
        if (surfaceHolder.surface.isValid) {
            canvas = surfaceHolder.lockCanvas()
            canvas.drawColor(Color.BLUE)
            
            mario.draw(canvas)
            for (platform in platforms) {
                platform.draw(canvas)
            }

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    private fun control() {
        Thread.sleep(17)
    }

    fun resume() {
        isPlaying = true
        thread.start()
    }

    fun pause() {
        isPlaying = false
        thread.join()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!mario.isJumping) {
                    mario.jump()
                }
            }
        }
        return true
    }
}