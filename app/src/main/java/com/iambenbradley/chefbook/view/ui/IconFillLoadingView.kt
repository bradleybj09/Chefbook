package com.iambenbradley.chefbook.view.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.iambenbradley.chefbook.R

class IconFillLoadingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var fillColor = context.getColor(R.color.colorPrimary)
    private var drawable = context.getDrawable(R.drawable.ic_app_icon_32)
    private val size = 160

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFill(canvas)
        drawDrawable(canvas)
    }

    private fun drawFill(canvas: Canvas?) {
        val radius = size / 2f
        paint.color = fillColor
        paint.style = Paint.Style.FILL
        canvas?.drawCircle(radius, radius, radius, paint)
    }

    private fun drawDrawable(canvas: Canvas?) {
        if (canvas != null) {
            drawable?.draw(canvas)
        }
    }
}