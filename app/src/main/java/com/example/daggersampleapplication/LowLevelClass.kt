package com.example.daggersampleapplication

import javax.inject.Inject
import kotlin.random.Random

interface ILowLevel{
    fun getBuildString(): String
}

// Assuming Low level class comes from an external library. Cannot add inject there.
class LowLevelClass constructor() : ILowLevel {
    override fun getBuildString(): String {
        if(BuildConfig.DEBUG) {
            return "LowLevel DEBUG" //+ Random(123).toString()
        }
        return "LowLevel NON_DEBUG" //+ Random(123).toString()
    }
}