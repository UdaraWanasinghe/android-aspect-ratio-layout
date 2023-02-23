package com.aureusapps.android.aspectratiolayout

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.core.view.children
import com.aureusapps.android.extensions.getFloatOrFraction
import com.aureusapps.android.extensions.obtainStyledAttributes

class AspectRatioLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.aspectRatioLayoutStyle,
    defStyleRes: Int = R.style.AspectRatioLayoutStyle
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    class LayoutParams : MarginLayoutParams {

        var aspectRatio: Float

        @SuppressLint("CustomViewStyleable")

        constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
            context.obtainStyledAttributes(attrs, R.styleable.AspectRatioLayout_Layout).apply {
                aspectRatio = getFloatOrFraction(R.styleable.AspectRatioLayout_Layout_layout_aspectRatio, -1f)
                recycle()
            }
        }

        constructor(width: Int, height: Int) : super(width, height) {
            aspectRatio = -1f
        }

        constructor(source: MarginLayoutParams?) : super(source) {
            aspectRatio = -1f
        }

        constructor(source: LayoutParams?) : super(source) {
            aspectRatio = source?.aspectRatio ?: -1f
        }

        constructor(source: ViewGroup.LayoutParams) : super(source) {
            aspectRatio = -1f
        }
    }

    private var parentAspectRatio: Float

    init {
        obtainStyledAttributes(
            attrs,
            R.styleable.AspectRatioLayout,
            defStyleAttr,
            defStyleRes
        ).apply {
            parentAspectRatio = getFloatOrFraction(R.styleable.AspectRatioLayout_aspectRatio, 1f)
            recycle()
        }
    }

    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }

    override fun generateLayoutParams(attrs: AttributeSet?): ViewGroup.LayoutParams {
        return LayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams?): ViewGroup.LayoutParams {
        if (p is LayoutParams) {
            return LayoutParams(p as LayoutParams?)
        } else if (p is MarginLayoutParams) {
            return LayoutParams(p as MarginLayoutParams?)
        }
        return LayoutParams(p)
    }

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams {
        return LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams?): Boolean {
        return p is LayoutParams
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var maxParentWidth = 0
        var maxParentHeight = 0

        for (child in children) {
            if (child.visibility != View.GONE) {

                val childLayoutParams = child.layoutParams as LayoutParams
                val marginLeft = childLayoutParams.leftMargin
                val marginTop = childLayoutParams.topMargin
                val marginRight = childLayoutParams.rightMargin
                val marginBottom = childLayoutParams.bottomMargin

                val horizontalSpacing = paddingLeft + paddingRight + marginLeft + marginRight
                val verticalSpacing = paddingTop + paddingBottom + marginTop + marginBottom

                var (containerWidth, containerHeight) = MeasureTool.findContainerSize(widthMeasureSpec, heightMeasureSpec, this)

                containerWidth -= horizontalSpacing
                containerHeight -= verticalSpacing

                val (childWidth, childHeight) = MeasureTool.measureChildView(
                    child,
                    containerWidth,
                    containerHeight,
                    parentAspectRatio
                )

                if (maxParentWidth < childWidth + horizontalSpacing) {
                    maxParentWidth = childWidth + horizontalSpacing
                }
                if (maxParentHeight < childHeight + verticalSpacing) {
                    maxParentHeight = childHeight + verticalSpacing
                }
            }
        }

        if (layoutParams.width == ViewGroup.LayoutParams.MATCH_PARENT) {
            val parentMeasureSpecWidth = MeasureSpec.getSize(widthMeasureSpec)
            if (parentMeasureSpecWidth > 0) {
                maxParentWidth = parentMeasureSpecWidth
            }
        }
        if (layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            val parentMeasureSpecHeight = MeasureSpec.getSize(heightMeasureSpec)
            if (parentMeasureSpecHeight > 0) {
                maxParentHeight = parentMeasureSpecHeight
            }
        }
        setMeasuredDimension(maxParentWidth, maxParentHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (child in children) {
            if (child.visibility != View.GONE) {
                val layoutParams = child.layoutParams as LayoutParams
                val leftSpacing = layoutParams.leftMargin + paddingLeft
                val topSpacing = layoutParams.topMargin + paddingTop
                child.layout(
                    l + leftSpacing,
                    t + topSpacing,
                    l + leftSpacing + child.measuredWidth,
                    t + topSpacing + child.measuredHeight
                )
            }
        }
    }

    fun setAspectRatio(aspectRatio: Float) {
        this.parentAspectRatio = aspectRatio
        requestLayout()
    }

}