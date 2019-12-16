package com.example.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.util.Log
import android.view.View


class BackgroundView(context: Context, attrs:AttributeSet) : View(context, attrs) {
    private val TAG = BackgroundView::class.java.simpleName

    private var margin = 30.0

    private val linePaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 15.0F
    }

    private var widthSize = 0;

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.d(TAG, "onLayout")
        super.onLayout(changed, left, top, right, bottom)

        margin = (right - left) * .1
    }

    override fun onDraw(canvas: Canvas) {
        Log.d(TAG, "onDraw" + this.width.toFloat())
        super.onDraw(canvas)

        //Horizontal Lines
        canvas.drawLine(margin.toFloat(), this.height.toFloat() * 0.33F, this.width.toFloat() - margin.toFloat(), this.height.toFloat() * 0.33F, linePaint)
        canvas.drawLine(margin.toFloat(), this.height.toFloat() * 0.66F, this.width.toFloat() - margin.toFloat(), this.height.toFloat() * 0.66F, linePaint)


        //Vertical Lines
        canvas.drawLine(this.width.toFloat() * 0.33F, margin.toFloat(), this.width.toFloat() * 0.33F, this.height.toFloat() - margin.toFloat(), linePaint)
        canvas.drawLine(this.width.toFloat() * 0.66F, margin.toFloat(), this.width.toFloat() * 0.66F, this.height.toFloat() - margin.toFloat(), linePaint)

    }
}