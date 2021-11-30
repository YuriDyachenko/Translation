package yuri.dyachenko.translation.di

import dagger.Module
import dagger.Provides
import yuri.dyachenko.translation.scheduler.DefaultSchedulers
import yuri.dyachenko.translation.scheduler.Schedulers

@Module
class SchedulersModule {

    @Provides
    fun provideSchedulers(): Schedulers = DefaultSchedulers()
}