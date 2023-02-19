package me.jorgetargz.ui.screens.welcome

import javafx.animation.Interpolator
import javafx.animation.TranslateTransition
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.util.Duration
import me.jorgetargz.ui.screens.common.BaseScreenController
import me.jorgetargz.ui.screens.common.ScreenConstants
import java.net.URL
import java.util.*


class WelcomeScreenController : Initializable, BaseScreenController() {

    @FXML
    lateinit var lbBienvenido: Label

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }


    override fun principalCargado() {
        animarPantalla()
        val welcome = ScreenConstants.WELCOME
        lbBienvenido.text = welcome
    }

    private fun animarPantalla() {
        val translate = TranslateTransition()
        translate.setNode(lbBienvenido)
        translate.setDuration(Duration.millis(1000.0))
        translate.setCycleCount(1)
        translate.setInterpolator(Interpolator.LINEAR)
        translate.setFromX(principalController?.width ?: 0.0)
        translate.setToX(lbBienvenido.layoutX)
        translate.play()
    }
}