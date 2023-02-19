package me.jorgetargz

import javafx.application.Application
import me.jorgetargz.di.appModule
import me.jorgetargz.ui.main.MainFX
import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    startKoin { modules(appModule) }
    Application.launch(MainFX::class.java, *args)
}