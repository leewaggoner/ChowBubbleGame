package com.wreckingball.chowbubble.graphics

import android.graphics.Canvas
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.strategies.SpriteDrawStatic
import com.wreckingball.chowbubble.strategies.SpriteUpdateLeftRight
import com.wreckingball.chowbubble.strategies.SpriteUpdateRightLeft
import com.wreckingball.chowbubble.strategies.SpriteUpdateUpDown
import com.wreckingball.chowbubble.utils.ScreenUtils
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Clouds : KoinComponent {
    private val screenUtils: ScreenUtils by inject()
    private val largeCloud: Sprite
    private val mediumCloud: Sprite
    private val smallCloud: Sprite

    init {
        val screenDims = screenUtils.screenDims
        largeCloud = Sprite(R.drawable.cloud_lg, TYPE_STATIC, SpriteUpdateUpDown(screenDims), SpriteDrawStatic())
        largeCloud.setXY(screenUtils.centerSpriteX(largeCloud, screenDims.x), screenUtils.pxToDp(940).toFloat())
        mediumCloud = Sprite(R.drawable.cloud_med, TYPE_STATIC, SpriteUpdateLeftRight(screenDims), SpriteDrawStatic())
        mediumCloud.setXY((screenDims.x - mediumCloud.getWidth() + screenUtils.pxToDp(128)).toFloat(), screenUtils.pxToDp(1316).toFloat())
        smallCloud = Sprite(R.drawable.cloud_sm, TYPE_STATIC, SpriteUpdateRightLeft(screenDims), SpriteDrawStatic())
        smallCloud.setXY((-screenUtils.pxToDp(128)).toFloat(), screenUtils.pxToDp(1252).toFloat())
    }

    fun onUpdate() {
        largeCloud.onUpdate()
        mediumCloud.onUpdate()
        smallCloud.onUpdate()
    }

    fun onDraw(frameBufferCanvas: Canvas) {
        largeCloud.onDraw(frameBufferCanvas)
        mediumCloud.onDraw(frameBufferCanvas)
        smallCloud.onDraw(frameBufferCanvas)
    }
}