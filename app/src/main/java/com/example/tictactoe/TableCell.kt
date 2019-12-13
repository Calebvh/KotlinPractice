package com.example.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class TableCell(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val TAG = BackgroundView::class.java.simpleName

    //States
    // 0 - Nothing Shown
    // 1 - X is shown
    // 2 - Circle is shown
    private var state = 0

    private var margin = 5.0

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 25.0F
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 25.0F
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

        //Draw an X
//        canvas.drawLine(margin.toFloat(), margin.toFloat(), this.width.toFloat() - margin.toFloat(), this.height.toFloat() - margin.toFloat(), linePaint)
//        canvas.drawLine(margin.toFloat(), this.height.toFloat() - margin.toFloat(), this.width.toFloat() - margin.toFloat(), margin.toFloat(), linePaint)


        //Draw Circle
        canvas.drawCircle(this.width.toFloat()/2, this.height.toFloat()/2, (this.width.toFloat() - margin.toFloat())/2, circlePaint)

    }



}