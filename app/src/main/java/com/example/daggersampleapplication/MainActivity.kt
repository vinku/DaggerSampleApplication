package com.example.daggersampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named

@Component(modules = [MyDaggerModule::class])
interface TopLevelComponent {
    // For constructor injection.
    fun topLevelProvider() : TopLevelClass
    // For Field injection
    fun inject(act: MainActivity)
}

@Module(includes = [ModuleForProvide::class])
interface MyDaggerModule {
    @Binds
    fun bindMid(middleLevelClass: MiddleLevelClass) : IMiddleLevel
}

@Module
object ModuleForProvide {
    @Provides
    @JvmStatic
    fun provideLowLevel() : ILowLevel {
        return LowLevelClass()
    }

    @Provides
    @JvmStatic
    @Named("MLString")
    fun provideMidLevelString(): String {
        return "MidLevel"
    }

    @Provides
    @JvmStatic
    @Named("LLString")
    fun provideLowLevelString(): String {
        return "LowLevel"
    }
}

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var topLevelClass: TopLevelClass;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // getting string via constructor injection
        val topLevelComponent = DaggerTopLevelComponent.create()
        val stringDagggerConstructorinjection = topLevelComponent.topLevelProvider().get()

        // For field injection, we need to call inject method or whatever is called
        topLevelComponent.inject(this)
        val stringFromFiledInjection = topLevelClass.get()

        findViewById<TextView>(R.id.MainText).text = stringDagggerConstructorinjection
    }
}