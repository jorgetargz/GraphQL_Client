package me.jorgetargz.ui.panel

import io.github.palexdev.materialfx.controls.MFXComboBox
import io.github.palexdev.materialfx.controls.MFXTableColumn
import io.github.palexdev.materialfx.controls.MFXTableView
import io.github.palexdev.materialfx.controls.MFXTextField
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.Label
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.modelo.Parada
import me.jorgetargz.ui.common.BaseScreenController
import java.net.URL
import java.util.*


class PanelController(
    private val panelViewModel: PanelViewModel = PanelViewModel()
) : Initializable, BaseScreenController() {

    @FXML
    lateinit var cargando: Label

    @FXML
    lateinit var tableLineas: MFXTableView<Linea>
    @FXML
    lateinit var idLineaColumn: MFXTableColumn<Linea>
    @FXML
    lateinit var numeroLineaColumn: MFXTableColumn<Linea>
    @FXML
    lateinit var tipoLineaColumn: MFXTableColumn<Linea>
    @FXML
    lateinit var idLineaTxt: MFXTextField
    @FXML
    lateinit var tipoLineaTxt: MFXTextField
    @FXML
    lateinit var numeroLineaTxt: MFXTextField

    @FXML
    lateinit var tableParadas: MFXTableView<Parada>
    @FXML
    lateinit var idParadaColumn: MFXTableColumn<Parada>
    @FXML
    lateinit var nombreParadaColumn: MFXTableColumn<Parada>
    @FXML
    lateinit var direccionParadaColumn: MFXTableColumn<Parada>
    @FXML
    lateinit var idParadaTxt: MFXTextField
    @FXML
    lateinit var nombreParadaTxt: MFXTextField
    @FXML
    lateinit var direccionParadaTxt: MFXTextField
    @FXML
    lateinit var lineasCmb: MFXComboBox<Linea>
    @FXML
    lateinit var encargadosCmb: MFXComboBox<Encargado>

    @FXML
    lateinit var tableEncargados: MFXTableView<Encargado>
    @FXML
    lateinit var idEncargadoColumn: MFXTableColumn<Encargado>
    @FXML
    lateinit var nombreEncargadoColumn: MFXTableColumn<Encargado>
    @FXML
    lateinit var dniEncargadoColumn: MFXTableColumn<Encargado>
    @FXML
    lateinit var idEncargadoTxt: MFXTextField
    @FXML
    lateinit var nombreEncargadoTxt: MFXTextField
    @FXML
    lateinit var dniEncargadoTxt: MFXTextField

    override fun initialize(location: URL?, resources: ResourceBundle?) {

        cargando.isVisible = false

        idLineaTxt.isEditable = false
        idParadaTxt.isEditable = false
        idEncargadoTxt.isEditable = false

        configTableLineas()
        configTableParadas()
        configTableEncargados()

        panelViewModel.handleEvent(PanelEvents.GetPanelData)

        stateCollector()
    }

    private fun stateCollector() {
        GlobalScope.launch(Dispatchers.JavaFx) {
            panelViewModel.uiState.collect { state ->
                cargando.isVisible = state.loading
                state.lineas.let { lineas ->
                    tableLineas.items.clear()
                    tableLineas.items.addAll(lineas)
                    lineasCmb.items.setAll(lineas)
                    encargadosCmb.items.setAll(lineas.flatMap { it.paradas }.map { it.encargado } )
                }
                state.paradas.let { paradas ->
                    tableParadas.items.setAll(paradas)
                }
                state.encargados.let { encargados ->
                    tableEncargados.items.setAll(encargados)
                }
                state.error?.let { error ->
                    principalController?.showAlert(Alert.AlertType.ERROR, "ERROR", error)
                    panelViewModel.handleEvent(PanelEvents.ClearErrors)
                }
            }
        }
    }

    private fun configTableLineas() {
        tableLineas.isFooterVisible = false
        tableLineas.selectionModel.setAllowsMultipleSelection(false)
        tableLineas.selectionModel.selectionProperty().addListener { _, _, newValue ->
            if (!newValue.isEmpty()) {
                val linea = newValue.values.first()
                idLineaTxt.text = linea.id.toString()
                tipoLineaTxt.text = linea.tipo
                numeroLineaTxt.text = linea.numero.toString()
                panelViewModel.handleEvent(PanelEvents.FilterParadas(linea))
            }
        }
        idLineaColumn.setRowCellFactory { _ -> MFXTableRowCell(Linea::id) }
        numeroLineaColumn.setRowCellFactory { _ -> MFXTableRowCell(Linea::numero) }
        tipoLineaColumn.setRowCellFactory { _ -> MFXTableRowCell(Linea::tipo) }
    }

    private fun configTableParadas() {
        tableParadas.isFooterVisible = false
        tableParadas.selectionModel.setAllowsMultipleSelection(false)
        tableParadas.selectionModel.selectionProperty().addListener { _, _, newValue ->
            if (newValue.isEmpty()) {
                idParadaTxt.text = ""
                nombreParadaTxt.text = ""
                direccionParadaTxt.text = ""
            } else {
                val parada = newValue.values.first()
                idParadaTxt.text = parada.id.toString()
                nombreParadaTxt.text = parada.nombre
                direccionParadaTxt.text = parada.direccion
                encargadosCmb.selectionModel.selectItem(parada.encargado)
                lineasCmb.selectionModel.selectItem(parada.linea)
                panelViewModel.handleEvent(PanelEvents.FilterEncargado(parada))
            }
        }
        idParadaColumn.setRowCellFactory { _ -> MFXTableRowCell(Parada::id) }
        nombreParadaColumn.setRowCellFactory { _ -> MFXTableRowCell(Parada::nombre) }
        direccionParadaColumn.setRowCellFactory { _ -> MFXTableRowCell(Parada::direccion) }
    }

    private fun configTableEncargados() {
        tableEncargados.isFooterVisible = false
        tableEncargados.selectionModel.setAllowsMultipleSelection(false)
        tableEncargados.selectionModel.selectionProperty().addListener { _, _, newValue ->
            if (newValue.isEmpty()) {
                idEncargadoTxt.text = ""
                nombreEncargadoTxt.text = ""
                dniEncargadoTxt.text = ""
            } else {
                val encargado = newValue.values.first()
                idEncargadoTxt.text = encargado.id.toString()
                nombreEncargadoTxt.text = encargado.nombre
                dniEncargadoTxt.text = encargado.dni
            }
        }
        idEncargadoColumn.setRowCellFactory { _ -> MFXTableRowCell(Encargado::id) }
        nombreEncargadoColumn.setRowCellFactory { _ -> MFXTableRowCell(Encargado::nombre) }
        dniEncargadoColumn.setRowCellFactory { _ -> MFXTableRowCell(Encargado::dni) }
    }

    @FXML
    private fun createLinea() {
        Linea(
            tipo = tipoLineaTxt.text,
            numero = numeroLineaTxt.text.toInt()
        ).let { linea ->
            panelViewModel.handleEvent(PanelEvents.CreateLinea(linea))
        }
    }

    @FXML
    private fun updateLinea() {
        Linea(
            id = idLineaTxt.text.toInt(),
            tipo = tipoLineaTxt.text,
            numero = numeroLineaTxt.text.toInt()
        ).let { linea ->
            panelViewModel.handleEvent(PanelEvents.UpdateLinea(linea))
        }
    }

    @FXML
    private fun deleteLinea() {
        panelViewModel.handleEvent(PanelEvents.DeleteLinea(Linea(idLineaTxt.text.toInt())))
    }

    fun createParada() {

    }

    fun updateParada() {

    }

    fun deleteParada() {

    }

    fun createEncargado() {

    }

    fun updateEncargado() {

    }

    fun deleteEncargado() {

    }

}