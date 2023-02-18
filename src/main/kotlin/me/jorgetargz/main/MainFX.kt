package me.jorgetargz.main

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import me.jorgetargz.main.common.Constantes
import me.jorgetargz.ui.main.MainController
import org.apache.logging.log4j.kotlin.logger
import java.util.*

class MainFX : Application() {

    override fun start(primaryStage: Stage?) {
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
        } catch (e: Exception) {
            logger().error("Error al cargar la pantalla principal", e)
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(MainFX::class.java, *args)
}
