package com.example.tictactoe

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class TableCell : View {
    private val TAG = BackgroundView::class.java.simpleName

    //States
    // 0 - Nothing Shown
    // 1 - X is shown
    // 2 - Circle is shown
    private var state = 0
    fun getState() : Int{return state}

    private var margin = 5.0

    private var colorFrom = Color.TRANSPARENT
    private var colorTo = Color.LTGRAY

    private val colorToAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        .setDuration(250)
    private val colorFromAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorTo, colorFrom)
        .setDuration(250)
    private var released = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        // -- Button Animation Down -- //
        // -- Button Animation Down -- //

        colorToAnimation.addUpdateListener { animator -> setBackgroundColor(animator.animatedValue as Int) }
        colorFromAnimation.addUpdateListener { animator -> setBackgroundColor(animator.animatedValue as Int) }

        colorToAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) { //                Log.d(TAG, "animation complete!");
                triggerClick()
            }
        })

        val listen = MyOnTouchListener()
        setOnTouchListener(listen)
    }

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

        when(state){
            0 -> {
                canvas.drawColor(Color.TRANSPARENT)
                Log.d("TAEST", " STATE")

            }
            1 -> {
                //Draw an X
                canvas.drawLine(margin.toFloat(), margin.toFloat(), this.width.toFloat() - margin.toFloat(), this.height.toFloat() - margin.toFloat(), linePaint)
                canvas.drawLine(margin.toFloat(), this.height.toFloat() - margin.toFloat(), this.width.toFloat() - margin.toFloat(), margin.toFloat(), linePaint)
            }
            2 -> {
                //Draw Circle
                canvas.drawCircle(this.width.toFloat()/2, this.height.toFloat()/2, (this.width.toFloat() - margin.toFloat())/2, circlePaint)
            }
            else -> {Log.d(TAG, "STATE ERROR")}
        }
        invalidate()

    }

    /**
     * If the button view is no longer being touched and the animation is no longer running then
     * Generate a click event for the view
     */
    private fun triggerClick() {
        if (released && !colorToAnimation.isRunning) {
            performClick()
        }
    }


    private inner class MyOnTouchListener : View.OnTouchListener{
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            when(event?.action){
                MotionEvent.ACTION_DOWN -> {
                    Log.d("TEST", "Action Down")
                    (v as TableCell).colorToAnimation.start()
                    released = false
                }
                MotionEvent.ACTION_UP -> {
                    Log.d("TEST", "Action Up")
                    if(checkBounds(v, event)){
                        released = true
                    }
                    (v as TableCell).colorFromAnimation.start()
                    triggerClick()
                }
                else -> Log.d("TEST", "Action Default Error")
            }
            return true
        }

        fun checkBounds(v: View, event: MotionEvent): Boolean {
            return event.x >= 0 && event.x <= v.width && event.y >= 0 && event.y <= v.height
        }
    }

    fun changeState(newState : Int){
        if(state == 0 || newState == 0){
            Log.d("TAEST", " FAS $newState")
            state = newState
        }

    }
}