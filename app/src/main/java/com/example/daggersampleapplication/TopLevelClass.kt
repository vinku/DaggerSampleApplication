package com.example.daggersampleapplication

import javax.inject.Inject

class TopLevelClass @Inject constructor(private val middleLevelClass: IMiddleLevel) {
    fun get() = "Toplevel " + middleLevelClass.getMidLevelString()
}