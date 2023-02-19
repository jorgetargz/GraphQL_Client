package me.jorgetargz.di

import me.jorgetargz.data.EncargadosRepository
import me.jorgetargz.data.LineasRepository
import me.jorgetargz.data.ParadasRepository
import me.jorgetargz.data.impl.EncargadosRepositoryImpl
import me.jorgetargz.data.impl.LineaRepositoryImpl
import me.jorgetargz.data.impl.ParadasRepositoryImpl
import me.jorgetargz.domain.services.EncargadosService
import me.jorgetargz.domain.services.LineasService
import me.jorgetargz.domain.services.ParadasService
import me.jorgetargz.domain.services.impl.EncargadosServiceImpl
import me.jorgetargz.domain.services.impl.LineaServiceImpl
import me.jorgetargz.domain.services.impl.ParadasServiceImpl
import me.jorgetargz.ui.screens.panel.PanelViewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    single { EncargadosRepositoryImpl() } bind EncargadosRepository::class
    single { LineaRepositoryImpl() } bind LineasRepository::class
    single { ParadasRepositoryImpl() } bind ParadasRepository::class

    single { EncargadosServiceImpl(get()) } bind EncargadosService::class
    single { LineaServiceImpl(get()) } bind LineasService::class
    single { ParadasServiceImpl(get()) } bind ParadasService::class

    single { PanelViewModel(get(), get(), get()) } bind PanelViewModel::class
}
