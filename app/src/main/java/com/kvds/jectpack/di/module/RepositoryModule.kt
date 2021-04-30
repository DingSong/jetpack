package com.kvds.jectpack.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    const val DISPATCHER_IO = "dispatcher_io"
    const val DISPATCHER_DEFAULT = "dispatcher_default"
    const val DISPATCHER_MAIN = "dispatcher_main"

    @Named(DISPATCHER_IO)
    @Provides
    fun dispatcherIO() = Dispatchers.IO

    @Named(DISPATCHER_DEFAULT)
    @Provides
    fun dispatcherDefault() = Dispatchers.Default

    @Named(DISPATCHER_MAIN)
    @Provides
    fun dispatcherMain() = Dispatchers.Main

}