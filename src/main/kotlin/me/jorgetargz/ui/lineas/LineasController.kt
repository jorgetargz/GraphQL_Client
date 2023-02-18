package me.jorgetargz.ui.lineas

import io.github.palexdev.materialfx.controls.MFXTableColumn
import io.github.palexdev.materialfx.controls.MFXTableView
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.Label
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.ui.common.BaseScreenController
import java.net.URL
import java.util.*


class LineasController : Initializable, BaseScreenController() {

    private val lineasViewModel = LineasViewModel()

    @FXML
    lateinit var cargando: Label

    @FXML
    lateinit var tableLineas: MFXTableView<Linea>
    @FXML
    lateinit var id: MFXTableColumn<Linea>
    @FXML
    lateinit var numero: MFXTableColumn<Linea>
    @FXML
    lateinit var tipo: MFXTableColumn<Linea>

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        cargando.isVisible = false

        id.setRowCellFactory { _ -> MFXTableRowCell(Linea::id) }
        numero.setRowCellFactory { _ -> MFXTableRowCell(Linea::numero) }
        tipo.setRowCellFactory { _ -> MFXTableRowCell(Linea::tipo) }

        lineasViewModel.handleEvent(LineasEvents.GetLineas)

        GlobalScope.launch(Dispatchers.JavaFx) {
            lineasViewModel.uiState.collect { state ->
                cargando.isVisible = state.loading
                state.lineas.let { lineas ->
                    tableLineas.items.clear()
                    tableLineas.items.addAll(lineas)
                }
                state.error?.let { error ->
                    principalController?.showAlert(Alert.AlertType.ERROR, "ERROR", error)
                    lineasViewModel.handleEvent(LineasEvents.ClearErrors)
                }
            }
        } }

}