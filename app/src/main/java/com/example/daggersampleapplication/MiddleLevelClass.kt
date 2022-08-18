package com.example.daggersampleapplication

import javax.inject.Inject
import javax.inject.Named

interface IMiddleLevel {
    fun getMidLevelString(): String
}
class MiddleLevelClass @Inject constructor(private val lowLevelClass: ILowLevel, @Named("MLString") val name: String) : IMiddleLevel {
    override fun getMidLevelString() = name + lowLevelClass.getBuildString()
}