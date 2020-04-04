package com.wreckingball.chowbubble.views

import android.content.Context
import android.graphics.PixelFormat
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.wreckingball.chowbubble.controllers.ChowController
import org.koin.core.KoinComponent
import org.koin.core.inject

private const val TICKS_PER_SECOND = 60
private const val SKIP_TICKS = 1000 / TICKS_PER_SECOND
private const val MAX_FRAMESKIP = 10

class ChowView(context: Context, attrs: AttributeSet) : SurfaceView(context, attrs), KoinComponent, Runnable {
    private val chowController: ChowController by inject()
    private val surfaceHolder: SurfaceHolder = holder
    private val renderThread: Thread = Thread(this)
    private var running: Boolean = false

    init {
        surfaceHolder.setFormat(PixelFormat.RGBA_8888)

        setOnTouchListener { _, event ->
            this@ChowView.chowController.onTouch(event)
            true
        }
        chowController.startGame()
    }

    fun resume() {
        running = true
        renderThread.start()
    }

    fun pause() {
        running = false
        while (true) {
            try {
                renderThread.join()
                return
            } catch (ex: InterruptedException) {
                //retry?
            }

        }
    }

    override fun run() {
        var nextGameTick = System.currentTimeMillis()
        var loops: Int

        while (running) {
            loops = 0
            while (System.currentTimeMillis() > nextGameTick && loops < MAX_FRAMESKIP) {
                chowController.updateChow()
                nextGameTick += SKIP_TICKS.toLong()
                loops++
            }

            if (!surfaceHolder.surface.isValid) {
                continue
            }
            val canvas = surfaceHolder.lockCanvas()
            chowController.drawChow(canvas)
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }
}