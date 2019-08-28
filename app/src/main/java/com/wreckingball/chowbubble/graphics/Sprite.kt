package com.wreckingball.chowbubble.graphics

import android.graphics.*
import com.wreckingball.chowbubble.strategies.SpriteDraw
import com.wreckingball.chowbubble.strategies.SpriteUpdate
import org.koin.core.KoinComponent
import org.koin.core.inject

const val TYPE_STATIC = 0
const val TYPE_BUBBLE_TEA = 1
const val TYPE_VEGGIE = 2
const val TYPE_BOMB = 3
const val TYPE_STAR = 4

class Sprite(resourceId: Int, val spriteType: Int, var updateStrategy: SpriteUpdate?, var drawStrategy: SpriteDraw?) : KoinComponent {

    private val spriteBitmapFactory: SpriteBitmapFactory by inject()
    private val sprites: MutableList<Bitmap> = mutableListOf()
    lateinit var box: RectF
    var currentSprite = 0
    var x: Float = 0f
    var y: Float = 0f
    var matrix: Matrix? = null
    val paint = Paint()

    init {
        val sprite = spriteBitmapFactory.getBitmap(resourceId)
        sprite?.let {
            sprites.add(sprite)
            box = RectF(0f, 0f, sprite.width.toFloat(), sprite.height.toFloat())
        }
    }

    fun addSprite(resourceId: Int) {
        val sprite = spriteBitmapFactory.getBitmap(resourceId)
        sprite?.let {
            sprites.add(sprite)
        }
    }

    fun incrementCurrentSprite() {
        currentSprite = (currentSprite + 1) % sprites.size
    }

    fun getBitmap(): Bitmap {
        return sprites[currentSprite]
    }

    fun getWidth(): Int {
        return sprites[currentSprite].width
    }

    fun getHeight(): Int {
        return sprites[currentSprite].height
    }

    fun setXY(x: Float, y: Float) {
        this.x = x
        this.y = y
        box.offset(x, y)
    }

    fun moveX(x: Float) {
        this.x += x
        box.offset(x, 0f)
    }

    fun moveY(y: Float) {
        this.y += y
        box.offset(0f, y)
    }

    fun onDraw(canvas: Canvas) {
        drawStrategy?.onDraw(canvas, this)
    }

    fun onUpdate() {
        updateStrategy?.onUpdate(this)
    }
}