/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.wallpaper.picker.customization.ui.view.transformer

import android.graphics.Point
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.android.wallpaper.R
import com.android.wallpaper.util.RtlUtils
import kotlin.math.abs

/**
 * This class implements the translations on a view pagers adjacent pages (adjacent to the currently
 * focused page) to make the page peek out from the end of the screen.
 */
class PreviewPagerPageTransformer(private val screenSizePx: Point) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val cardPreview = page.requireViewById<View>(R.id.preview_card)

        val nextItemVisibleOffsetPx =
            page.resources.getDimension(R.dimen.wallpaper_control_button_group_divider_space)

        // device width in pixels minus the page width will give margin
        val availableMargin = screenSizePx.x - cardPreview.width - nextItemVisibleOffsetPx
        page.translationX =
            if (RtlUtils.isRtl(page.context)) {
                availableMargin * position
            } else {
                -availableMargin * position
            }

        page.alpha = 0.25f + (1 - abs(position))
    }
}
