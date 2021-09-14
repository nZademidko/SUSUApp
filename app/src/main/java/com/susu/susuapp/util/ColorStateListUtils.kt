package com.susu.susuapp.util

import android.content.res.ColorStateList
import android.graphics.Color

class ColorStateListUtils {

    companion object {

        fun getColorStateList(
            enabledColor: Int = Color.parseColor("#00BFFF"), // Capri
            disabledColor: Int = Color.parseColor("#A2A2D0"), // Blue bell
            checkedColor: Int = Color.parseColor("#7BB661"), // Bud green
            uncheckedColor: Int = Color.parseColor("#A3C1AD"), // Cambridge blue
            activeColor: Int = Color.parseColor("#1034A6"), // Egyptian blue
            inactiveColor: Int = Color.parseColor("#614051"), // Eggplant
            pressedColor: Int = Color.parseColor("#FFD300"), // Cyber yellow
            focusedColor: Int = Color.parseColor("#00FFFF") // Aqua): ColorStateList)
        ): ColorStateList {
            val states = arrayOf(
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_active),
                intArrayOf(-android.R.attr.state_active),
                intArrayOf(android.R.attr.state_pressed),
                intArrayOf(android.R.attr.state_focused)
            )
            val colors = intArrayOf(
                enabledColor, // enabled
                disabledColor, // disabled
                checkedColor, // checked
                uncheckedColor, // unchecked
                activeColor, // active
                inactiveColor, // inactive
                pressedColor, // pressed
                focusedColor // focused
            )
            return ColorStateList(states, colors)
        }
    }
}