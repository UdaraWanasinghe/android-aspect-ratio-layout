package com.aureusapps.android.aspectratiolayout

import android.content.Context
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.aureusapps.android.aspectratiolayout.AspectRatioLayout.Companion.LayoutParams
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.roundToInt

/**
 * M - MatchParent
 * W - WrapContent
 * E - ExactSize
 * P - Padding
 * G - Margin
 */
@RunWith(AndroidJUnit4::class)
class AspectRatioLayoutTest {

    // ParentMM_ChildXX

    @Test
    fun test_ParentMM_ChildMM() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMM_ChildMM",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 1920,
            expectedChildWidth = 1080,
            expectedChildHeight = 720
        )
    }

    /**
     * Unspecified width
     * Width not zero
     */
    @Test
    fun test_ParentMM_ChildMM2() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMM_ChildMM",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT),
            parentWidthMeasureSpec = MeasureSpec.makeMeasureSpec(1080, MeasureSpec.UNSPECIFIED),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 1920,
            expectedChildWidth = 1080,
            expectedChildHeight = 720
        )
    }

    /**
     * Unspecified width
     * Width measure spec value is zero
     */
    @Test
    fun test_ParentMM_ChildMM3() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMM_ChildMM3",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT),
            parentWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 2880,
            expectedParentHeight = 1920,
            expectedChildWidth = 2880,
            expectedChildHeight = 1920
        )
    }

    /**
     * Unspecified height
     * Height measure spec value is zero
     */
    @Test
    fun test_ParentMM_ChildMM4() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMM_ChildMM4",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT),
            parentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 720,
            expectedChildWidth = 1080,
            expectedChildHeight = 720
        )
    }

    /**
     * Unspecified height
     * Height measure spec value is not zero
     */
    @Test
    fun test_ParentMM_ChildMM5() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMM_ChildMM4",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT),
            parentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(1920, MeasureSpec.UNSPECIFIED),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 1920,
            expectedChildWidth = 1080,
            expectedChildHeight = 720
        )
    }

    @Test
    fun test_ParentMM_ChildWM() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMM_ChildWM",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(WRAP_CONTENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 1920,
            expectedChildWidth = 1080,
            expectedChildHeight = 720
        )
    }

    @Test
    fun test_ParentMM_ChildMW() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMM_ChildMW",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 1920,
            expectedChildWidth = 1080,
            expectedChildHeight = 720
        )
    }

    @Test
    fun test_ParentMM_ChildWW() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMM_ChildWW",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 1920,
            expectedChildWidth = 36,
            expectedChildHeight = 24
        )
    }

    // ParentXX_ChildMM

    @Test
    fun test_ParentMW_ChildMM() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMW_ChildMM",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 720,
            expectedChildWidth = 1080,
            expectedChildHeight = 720
        )
    }

    @Test
    fun test_ParentWM_ChildMM() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentWM_ChildMM",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(WRAP_CONTENT, MATCH_PARENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 1920,
            expectedChildWidth = 1080,
            expectedChildHeight = 720
        )
    }

    @Test
    fun test_ParentWM_ChildMM2() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentWM_ChildMM2",
            displayWidth = 1920,
            displayHeight = 1080,
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(WRAP_CONTENT, MATCH_PARENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1620,
            expectedParentHeight = 1080,
            expectedChildWidth = 1620,
            expectedChildHeight = 1080
        )
    }

    @Test
    fun test_ParentWW_ChildMM() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentWW_ChildMM",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 720,
            expectedChildWidth = 1080,
            expectedChildHeight = 720
        )
    }

    // test_ParentWW_ChildEE

    @Test
    fun test_ParentWW_ChildEE() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentWW_ChildEE",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(40, 40).apply { aspectRatio = 1.5f },
            expectedParentWidth = 40,
            expectedParentHeight = 40,
            expectedChildWidth = 40,
            expectedChildHeight = 40
        )
    }

    // fixed child width

    @Test
    fun test_ParentWW_ChildEM() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentMM_ChildEM",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(36, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 36,
            expectedParentHeight = 24,
            expectedChildWidth = 36,
            expectedChildHeight = 24
        )
    }

    // with padding

    @Test
    fun test_ParentWW_ChildMM_Padding() {
        testLayouts(
            appContext = InstrumentationRegistry.getInstrumentation().targetContext,
            textName = "test_ParentWW_ChildMM_Padding",
            parentAspectRatio = 1.2f,
            parentLayoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT),
            childAspectRatio = 1.5f,
            childLayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { aspectRatio = 1.5f },
            expectedParentWidth = 1080,
            expectedParentHeight = 725,
            expectedChildWidth = 1064,
            expectedChildHeight = 709,
            childPadding = 8,
            childMargin = 8
        )
    }

    private fun testLayouts(
        appContext: Context,
        textName: String,
        displayWidth: Int = 1080,
        displayHeight: Int = 1920,
        childAspectRatio: Float = 1.5f,
        childLayoutParams: LayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            .apply {
                aspectRatio = childAspectRatio
            },
        parentLayoutParams: ViewGroup.LayoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT),
        minChildWidth: Int = 24,
        minChildHeight: Int = 24,
        parentAspectRatio: Float = 1.2f,
        parentWidthMeasureSpec: Int = MeasureSpec.makeMeasureSpec(displayWidth, MeasureSpec.AT_MOST),
        parentHeightMeasureSpec: Int = MeasureSpec.makeMeasureSpec(displayHeight, MeasureSpec.AT_MOST),
        expectedParentWidth: Int = displayWidth,
        expectedParentHeight: Int = (displayWidth / childAspectRatio).roundToInt(),
        expectedChildWidth: Int = displayWidth,
        expectedChildHeight: Int = (displayWidth / childAspectRatio).roundToInt(),
        childPadding: Int = 0,
        childMargin: Int = 0
    ) {
        val (parentLayout, childLayout) = createLayouts(
            appContext,
            parentAspectRatio,
            parentWidthMeasureSpec,
            parentHeightMeasureSpec,
            parentLayoutParams,
            childLayoutParams,
            minChildWidth,
            minChildHeight,
            childPadding,
            childMargin
        )
        checkLayouts(
            textName,
            parentLayout,
            childLayout,
            expectedParentWidth,
            expectedParentHeight,
            expectedChildWidth,
            expectedChildHeight
        )
    }

    private fun createLayouts(
        context: Context,
        parentAspectRatio: Float,
        parentWidthMeasureSpec: Int,
        parentHeightMeasureSpec: Int,
        parentLayoutParams: ViewGroup.LayoutParams,
        childLayoutParams: LayoutParams,
        minChildWidth: Int,
        minChildHeight: Int,
        childPadding: Int,
        childMargin: Int
    ): Pair<View, View> {
        val aspectRatioLayout = AspectRatioLayout(context)
            .apply {
                layoutParams = parentLayoutParams
                setAspectRatio(parentAspectRatio)
            }
        val minSizedView = MinSizedView(context, minChildWidth, minChildHeight)
            .apply {
                layoutParams = childLayoutParams
                    .apply {
                        setMargins(childMargin)
                    }
                setPadding(childPadding)
            }
        aspectRatioLayout.addView(minSizedView)

        aspectRatioLayout.measure(parentWidthMeasureSpec, parentHeightMeasureSpec)
        aspectRatioLayout.layout(0, 0, aspectRatioLayout.measuredWidth, aspectRatioLayout.measuredHeight)
        return aspectRatioLayout to minSizedView
    }

    private fun checkLayouts(
        testName: String,
        parentLayout: View,
        childLayout: View,
        expectedParentWidth: Int,
        expectedParentHeight: Int,
        expectedChildWidth: Int,
        expectedChildHeight: Int
    ) {
        Assert.assertEquals("${testName}_parentWidth", expectedParentWidth, parentLayout.width)
        Assert.assertEquals("${testName}_parentHeight", expectedParentHeight, parentLayout.height)

        Assert.assertEquals("${testName}_childWidth", expectedChildWidth, childLayout.width)
        Assert.assertEquals("${testName}_childHeight", expectedChildHeight, childLayout.height)
    }

    private class MinSizedView(
        context: Context,
        minWidth: Int,
        minHeight: Int
    ) : LinearLayout(context, null, 0) {

        init {
            val linearLayout = LinearLayout(context)
            linearLayout.layoutParams = LayoutParams(minWidth, minHeight)
            addView(linearLayout)
        }

    }
}