package yuri.dyachenko.translation.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import yuri.dyachenko.translation.ui.MainActivity
import yuri.dyachenko.translation.ui.words.WordsFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        RetrofitModule::class,
        SchedulersModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(appContext: Context): Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(wordsFragment: WordsFragment)
}