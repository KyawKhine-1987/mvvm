package com.android.freelance.mvvm

import android.app.Application
import com.android.freelance.mvvm.data.db.entities.AppDatabase
import com.android.freelance.mvvm.data.network.MyRestApi
import com.android.freelance.mvvm.data.network.NetworkConnectionInterceptor
import com.android.freelance.mvvm.data.preferences.PreferenceProvider
import com.android.freelance.mvvm.data.repositories.QuotesRepository
import com.android.freelance.mvvm.data.repositories.UserRepository
import com.android.freelance.mvvm.ui.viewmodels.AuthViewModelFactory
import com.android.freelance.mvvm.ui.viewmodels.ProfileViewModelFactory
import com.android.freelance.mvvm.ui.viewmodels.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance())}
        bind() from singleton { MyRestApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuotesRepository(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
        /*bind() from singleton { NetworkConnectionInterceptor(instance()) }*/
    }
}