package com.kyawlinnthant.navigation.navigator

import com.everest.navigation.navigator.AppNavigator
import com.everest.navigation.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    @Singleton
    fun bindsNavigator(navigator: AppNavigatorImpl): AppNavigator
}
