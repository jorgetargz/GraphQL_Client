package me.jorgetargz.ui.screens.main

import io.github.palexdev.materialfx.font.MFXFontIcon
import javafx.application.Platform
import javafx.css.PseudoClass
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Alert
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.stage.Stage
import me.jorgetargz.ui.screens.common.BaseScreenController
import me.jorgetargz.ui.screens.common.ScreenConstants
import me.jorgetargz.ui.screens.common.Screens
import java.io.IOException
import java.util.*
import kotlin.system.exitProcess


class MainController() {
    private val alert: Alert = Alert(Alert.AlertType.NONE)

    private var primaryStage: Stage? = null
    private var xOffset = 0.0
    private var yOffset = 0.0

    @FXML
    lateinit var root: BorderPane

    @FXML
    lateinit var windowHeader: HBox

    @FXML
    lateinit var closeIcon: MFXFontIcon

    @FXML
    lateinit var minimizeIcon: MFXFontIcon

    @FXML
    lateinit var alwaysOnTopIcon: MFXFontIcon

    @FXML
    lateinit var menuPrincipal: MenuBar

    fun setStage(stage: Stage?) {
        primaryStage = stage
    }

    fun initialize() {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED) { _ : MouseEvent? -> exit() }
        minimizeIcon.addEventHandler(
            MouseEvent.MOUSE_CLICKED
        ) { _ : MouseEvent? -> (root.scene?.window as Stage).isIconified = true }
        alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED) { _ : MouseEvent? ->
            val newVal = !primaryStage!!.isAlwaysOnTop
            alwaysOnTopIcon.pseudoClassStateChanged(
                PseudoClass
                    .getPseudoClass(ScreenConstants.ALWAYS_ON_TOP), newVal
            )
            primaryStage!!.isAlwaysOnTop = newVal
        }
        windowHeader.onMousePressed = EventHandler { event: MouseEvent ->
            xOffset = primaryStage!!.x - event.screenX
            yOffset = primaryStage!!.y - event.screenY
        }
        windowHeader.onMouseDragged = EventHandler { event: MouseEvent ->
            primaryStage!!.x = event.screenX + xOffset
            primaryStage!!.y = event.screenY + yOffset
        }
        menuPrincipal.isVisible = true
        cargarPantalla(Screens.WELCOME)
    }

    val width: Double
        get() = root.scene?.window?.width ?: 0.0

    fun showAlert(alertType: Alert.AlertType?, titulo: String?, mensaje: String?) {
        alert.alertType = alertType
        alert.title = titulo
        alert.headerText = titulo
        alert.contentText = mensaje
        alert.showAndWait()
    }

    private fun cargarPantalla(pantalla: Screens) {
        val panePantalla: Pane
        val r: ResourceBundle = ResourceBundle.getBundle(ScreenConstants.I_18_N_TEXTS_UI, Locale.ENGLISH)
        try {
            val fxmlLoader = FXMLLoader()
            fxmlLoader.resources = r
            panePantalla = fxmlLoader.load(javaClass.getResourceAsStream(pantalla.path))
            val pantallaController: BaseScreenController = fxmlLoader.getController()
            pantallaController.principalController = this
            pantallaController.principalCargado()
            root.center = panePantalla
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @FXML
    private fun menuOnClick(actionEvent: ActionEvent) {
        when ((actionEvent.source as MenuItem).id) {
            ScreenConstants.ADMIN_PANEL_MANU_ITEM -> cargarPantalla(Screens.PANEL)
            else -> cargarPantalla(Screens.WELCOME)
        }
    }

    @FXML
    private fun cambiarcss() {
        if (primaryStage!!.scene.root.stylesheets.stream().findFirst().isEmpty
            || (primaryStage!!.scene.root.stylesheets.stream().findFirst().isPresent
                    && primaryStage!!.scene.root.stylesheets.stream().findFirst().get().contains(ScreenConstants.STYLE))
        ) {
            try {
                javaClass.getResource(ScreenConstants.CSS_DARKMODE_CSS)?.toExternalForm().run {
                    primaryStage!!.scene.root.stylesheets.clear()
                    primaryStage!!.scene.root.stylesheets.add(this)
                }
            } catch (e: Exception) {
                println(e.message)
            }
        } else {
            try {
                javaClass.getResource(ScreenConstants.CSS_STYLE_CSS)?.toExternalForm().run {
                    primaryStage!!.scene.root.stylesheets.clear()
                    primaryStage!!.scene.root.stylesheets.add(this)
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    @FXML
    private fun acercaDe() {
        showAlert(Alert.AlertType.INFORMATION, ScreenConstants.ABOUT, ScreenConstants.AUTHOR_DATA)
    }

    @FXML
    private fun exit() {
        Platform.exit()
        exitProcess(0)
    }
}