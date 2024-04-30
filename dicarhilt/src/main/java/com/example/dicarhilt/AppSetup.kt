package com.example.dicarhilt

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent

@HiltAndroidApp
class MyApp : Application() {
}
@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideEngine(): Engine {
        return GasEngine()  // or ElectricEngine()
    }
}
