package yuri.dyachenko.translation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yuri.dyachenko.translation.ui.words.WordsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun provideViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WordsViewModel::class)
    abstract fun provideWordsViewModel(
        translationViewModel: WordsViewModel
    ): ViewModel
}