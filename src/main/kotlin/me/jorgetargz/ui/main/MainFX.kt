package me.jorgetargz.ui.main

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import me.jorgetargz.ui.main.common.Constantes
import me.jorgetargz.ui.screens.main.MainController
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*

class MainFX : Application() {

    override fun start(primaryStage: Stage?) {
        val logger: Logger = LogManager.getLogger(MainFX::class.java)
        try {
            val textResources = ResourceBundle.getBundle(Constantes.I_18_N_TEXTS_UI, Locale.ENGLISH)

            val loader = FXMLLoader(
                javaClass.getResource(Constantes.PATH_FXML),
                textResources
            )

            val root: BorderPane? = loader.load()

            loader.getController<MainController>().setStage(primaryStage)

            val scene = Scene(root)
            scene.fill = Color.TRANSPARENT

            primaryStage?.title = textResources.getString(Constantes.TITULO)
            primaryStage?.scene = scene
            primaryStage?.isResizable = false
            primaryStage?.scene?.stylesheets?.add(javaClass.getResource(Constantes.CSS_STYLE_CSS)?.toExternalForm())
            primaryStage?.title = textResources.getString(Constantes.APP_TITLE)
            primaryStage?.initStyle(StageStyle.TRANSPARENT)
            primaryStage?.show()

            logger.info("Pantalla principal cargada")
        } catch (e: Exception) {
            logger.error("Error al cargar la pantalla principal", e)
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(MainFX::class.java, *args)
}
