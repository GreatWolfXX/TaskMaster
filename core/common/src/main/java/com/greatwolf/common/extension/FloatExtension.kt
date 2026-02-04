package com.greatwolf.common.extension

fun Float.normalize(min: Float, max: Float): Float {
    if (max == min) return 0f
    return (this - min) / (max - min)
}