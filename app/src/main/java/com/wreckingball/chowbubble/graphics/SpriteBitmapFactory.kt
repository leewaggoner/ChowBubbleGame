package com.wreckingball.chowbubble.graphics

import android.content.Context
import android.graphics.*
import com.wreckingball.chowbubble.R
import com.wreckingball.chowbubble.utils.ScreenUtils
import org.koin.core.KoinComponent
import org.koin.core.inject

class SpriteBitmapFactory(private val context: Context) : KoinComponent {
    private val screenUtils: ScreenUtils by inject()
    private var bitmapHolder: MutableMap<Int, Bitmap> = hashMapOf()
    private lateinit var scaleDims: PointF

    init {
        var bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.game_background)
        bitmap = processBackground(bitmap, screenUtils.screenDims)
        bitmapHolder[R.drawable.game_background] = bitmap

    }

    fun getBitmap(resourceId: Int) : Bitmap? {
        return bitmapHolder[resourceId]
    }

    private fun processBackground(background: Bitmap, screenDims: Point): Bitmap {
        //scale and center the background
        val sourceWidth = background.width
        val sourceHeight = background.height

        //compute the scaling factors to fit the new height and width, respectively.
        //to cover the final image, the final scaling will be the bigger
        //of these two
        val xScale = screenDims.x.toFloat() / sourceWidth
        val yScale = screenDims.y.toFloat() / sourceHeight
        val scale = Math.max(xScale, yScale)

        //save the scaling info for use with other bitmaps
        scaleDims = PointF(xScale, yScale)

        // Now get the size of the source bitmap when scaled
        val scaledWidth = scale * sourceWidth
        val scaledHeight = scale * sourceHeight

        // Let's find out the upper left coordinates if the scaled bitmap
        // should be centered in the new size given by the parameters
        val left = (screenDims.x - scaledWidth) / 2
        val top = (screenDims.y - scaledHeight) / 2

        // The target rectangle for the new, scaled version of the source bitmap will now be:
        val targetRect = RectF(left, top, left + scaledWidth, top + scaledHeight)

        // Create a new bitmap of the specified size and draw our new scaled bitmap onto it.
        val result = Bitmap.createBitmap(screenDims.x, screenDims.y, background.config)
        val canvas = Canvas(result)
        canvas.drawBitmap(background, null, targetRect, null)

        background.recycle()

        return result
    }
}