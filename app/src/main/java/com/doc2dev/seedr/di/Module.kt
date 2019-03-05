package com.doc2dev.seedr.di

import com.doc2dev.seedr.SeedrApp
import com.doc2dev.seedr.repository.SeedRepository
import com.doc2dev.seedr.viewmodel.SeedViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {
    factory { SeedrApp.INSTANCE.getDatabase().seedDao() }
    factory { SeedRepository(get()) }
    viewModel { SeedViewModel(get()) }
}