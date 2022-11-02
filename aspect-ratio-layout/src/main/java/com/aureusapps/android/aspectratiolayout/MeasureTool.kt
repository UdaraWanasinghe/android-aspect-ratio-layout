package com.aureusapps.android.aspectratiolayout

import android.view.View
import android.view.ViewGroup
import kotlin.math.roundToInt
import com.aureusapps.android.aspectratiolayout.AspectRatioLayout.Companion.LayoutParams

internal class MeasureTool {
    companion object {

        fun findContainerSize(
            widthMeasureSpec: Int,
            heightMeasureSpec: Int,
            parentLayout: View
        ): Pair<Int, Int> {
            val layoutParams = parentLayout.layoutParams
            val containerWidth = findContainerLength(widthMeasureSpec, layoutParams.width)
            val containerHeight = findContainerLength(heightMeasureSpec, layoutParams.height)
            return containerWidth to containerHeight
        }

        private fun findContainerLength(
            measureSpec: Int,
            layoutLength: Int
        ): Int {
            return if (layoutLength < 0) {
                val mode = View.MeasureSpec.getMode(measureSpec)
                val size = View.MeasureSpec.getSize(measureSpec)
                when {
                    mode != View.MeasureSpec.UNSPECIFIED -> {
                        View.MeasureSpec.getSize(measureSpec)
                    }
                    size > 0 -> {
                        size
                    }
                    else -> {
                        Int.MAX_VALUE
                    }
                }
            } else {
                layoutLength
            }
        }

        fun measureChildView(
            childLayout: View,
            containerWidth: Int,
            containerHeight: Int,
            parentAspectRatio: Float
        ): Pair<Int, Int> {
            val childLayoutParams = childLayout.layoutParams as ViewGroup.LayoutParams
            val childLayoutWidth = childLayoutParams.width
            val childLayoutHeight = childLayoutParams.height
            val (minChildWidth, minChildHeight) = childLayout.getMinSize()

            return when (childLayoutWidth) {
                ViewGroup.LayoutParams.WRAP_CONTENT -> {
                    when (childLayoutHeight) {
                        ViewGroup.LayoutParams.WRAP_CONTENT -> {
                            fitChildToContainer(
                                childLayout,
                                minChildWidth,
                                minChildHeight,
                                parentAspectRatio,
                                false
                            )
                        }
                        ViewGroup.LayoutParams.MATCH_PARENT -> {
                            if (containerWidth == Int.MAX_VALUE && containerHeight == Int.MAX_VALUE) {
                                fitChildToContainer(
                                    childLayout,
                                    minChildWidth,
                                    minChildHeight,
                                    parentAspectRatio,
                                    false
                                )
                            } else {
                                fitChildToContainer(
                                    childLayout,
                                    containerWidth,
                                    containerHeight,
                                    parentAspectRatio,
                                    true
                                )
                            }
                        }
                        else -> {
                            fitChildToContainer(
                                childLayout,
                                containerWidth,
                                childLayoutHeight,
                                parentAspectRatio,
                                true
                            )
                        }
                    }
                }
                ViewGroup.LayoutParams.MATCH_PARENT -> {
                    when (childLayoutHeight) {
                        ViewGroup.LayoutParams.WRAP_CONTENT -> {
                            if (containerWidth == Int.MAX_VALUE && containerHeight == Int.MAX_VALUE) {
                                fitChildToContainer(
                                    childLayout,
                                    minChildWidth,
                                    minChildHeight,
                                    parentAspectRatio,
                                    false
                                )
                            } else {
                                fitChildToContainer(
                                    childLayout,
                                    containerWidth,
                                    containerHeight,
                                    parentAspectRatio,
                                    true
                                )
                            }
                        }
                        ViewGroup.LayoutParams.MATCH_PARENT -> {
                            if (containerWidth == Int.MAX_VALUE && containerHeight == Int.MAX_VALUE) {
                                fitChildToContainer(
                                    childLayout,
                                    minChildWidth,
                                    minChildHeight,
                                    parentAspectRatio,
                                    false
                                )
                            } else {
                                fitChildToContainer(
                                    childLayout,
                                    containerWidth,
                                    containerHeight,
                                    parentAspectRatio,
                                    true
                                )
                            }
                        }
                        else -> {
                            fitChildToContainer(
                                childLayout,
                                containerWidth,
                                childLayoutHeight,
                                parentAspectRatio,
                                true
                            )
                        }
                    }
                }
                else -> {
                    when (childLayoutHeight) {
                        ViewGroup.LayoutParams.WRAP_CONTENT -> {
                            fitChildToContainer(
                                childLayout,
                                childLayoutWidth,
                                containerHeight,
                                parentAspectRatio,
                                true
                            )
                        }
                        ViewGroup.LayoutParams.MATCH_PARENT -> {
                            fitChildToContainer(
                                childLayout,
                                childLayoutWidth,
                                containerHeight,
                                parentAspectRatio,
                                true
                            )
                        }
                        else -> {
                            childLayout.measure(
                                View.MeasureSpec.makeMeasureSpec(childLayoutWidth, View.MeasureSpec.EXACTLY),
                                View.MeasureSpec.makeMeasureSpec(childLayoutHeight, View.MeasureSpec.EXACTLY)
                            )
                            childLayoutWidth to childLayoutHeight
                        }
                    }
                }
            }
        }

        private fun fitChildToContainer(
            childLayout: View,
            containerWidth: Int,
            containerHeight: Int,
            parentAspectRatio: Float,
            fitInside: Boolean
        ): Pair<Int, Int> {
            val measuredWidth: Int
            val measuredHeight: Int
            val containerAspectRatio = containerWidth.toFloat().div(containerHeight)

            val childLayoutParams = childLayout.layoutParams as LayoutParams
            val childAspectRatio = if (childLayoutParams.aspectRatio > 0) childLayoutParams.aspectRatio else parentAspectRatio

            if (fitInside && containerAspectRatio < childAspectRatio || !fitInside && containerAspectRatio > childAspectRatio) {
                measuredWidth = containerWidth
                measuredHeight = childAspectRatio.findHeight(measuredWidth)
            } else {
                measuredHeight = containerHeight
                measuredWidth = childAspectRatio.findWidth(measuredHeight)
            }
            childLayout.measure(
                View.MeasureSpec.makeMeasureSpec(measuredWidth, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY)
            )
            return measuredWidth to measuredHeight
        }

        private fun Float.findWidth(height: Int): Int {
            return (height * this).roundToInt()
        }

        private fun Float.findHeight(width: Int): Int {
            return (width / this).roundToInt()
        }

        private fun View.getMinSize(): Pair<Int, Int> {
            measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            return measuredWidth to measuredHeight
        }
    }
}