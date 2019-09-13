package com.cryppy.di.component

import android.app.Application
import com.cryppy.CryppyApplication
import com.cryppy.di.module.ActivityModule
import com.cryppy.di.module.AppModule
import com.cryppy.di.module.DomainModule
import com.cryppy.di.module.FragmentModule
import com.cryppy.di.module.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        PresentationModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: CryppyApplication)
}