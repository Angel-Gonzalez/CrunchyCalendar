package ru.cleverpumpkin.calendar.decorations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import ru.cleverpumpkin.calendar.CalendarDateView
import ru.cleverpumpkin.calendar.R
import ru.cleverpumpkin.calendar.utils.dpToPix
import ru.cleverpumpkin.calendar.utils.getColorInt

internal class GridDividerItemDecoration(
    context: Context,
    @ColorInt dividerColor: Int = context.getColorInt(R.color.calendar_grid_color),
    private val drawGridOnSelectedDates: Boolean
) : RecyclerView.ItemDecoration() {

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = dividerColor
        strokeWidth = context.dpToPix(1.0f)
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)

            if (child is CalendarDateView) {
                if (child.isDateSelected && drawGridOnSelectedDates.not()) {
                    continue
                }

                canvas.drawRect(
                    child.left.toFloat(),
                    child.top.toFloat(),
                    child.right.toFloat(),
                    child.bottom.toFloat(),
                    linePaint
                )
            }
        }
    }
}