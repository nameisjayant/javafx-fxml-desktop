package com.nameisjayant.demo.data.main

import com.nameisjayant.demo.data.model.CsvDataModel
import com.nameisjayant.demo.utils.*
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.Event
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox


class IncidentController {

    @FXML
    private lateinit var incidentTime: TextField

    @FXML
    private lateinit var subrubIncidentOccurred: TextField

    @FXML
    private lateinit var streetIncidentOccurred: TextField

    @FXML
    private lateinit var ageCategorySuspect: TextField

    @FXML
    private lateinit var noOfSuspectInvolved: TextField

    @FXML
    private lateinit var damage: TextField

    @FXML
    private lateinit var estimateLoss: TextField

    @FXML
    private lateinit var detailOfInjury: TextField

    @FXML
    private lateinit var residentWhoReported: TextField
    private val incidentType = TextField()

    @FXML
    lateinit var incidentParent: VBox

    @FXML
    private lateinit var injuryParent: VBox
    private val injury = TextField()

    @FXML
    private fun logout(event: Event) {
        loadScreen(Path.HOME_PATH, event) {
            setPref(PreferenceStore.index, "0")
        }
    }

    @FXML
    private fun addIncidentDetail() {
        addIncidentInformation()
    }

    @FXML
    private lateinit var radioGroup: HBox
    private val currentGender = SimpleStringProperty()


    @FXML
    private fun initialize() {
        createRadioGroup()
        addIncidentTypeDropDown()
        addInjuryDropDown()
    }


    private fun createRadioGroup() {
        val toggleGroup = ToggleGroup()
        val maleRadioButton = RadioButton("Male")
        val femaleRadioButton = RadioButton("Female")
        val otherRadioButton = RadioButton("Other")
        maleRadioButton.toggleGroup = toggleGroup
        femaleRadioButton.toggleGroup = toggleGroup
        otherRadioButton.toggleGroup = toggleGroup
        radioGroup.children.addAll(maleRadioButton, femaleRadioButton, otherRadioButton)
        toggleGroup.selectedToggleProperty()
            .addListener { _: ObservableValue<out Toggle?>?, _: Toggle?, newValue: Toggle? ->
                if (newValue != null) {
                    val selectedRadioButton = newValue as RadioButton
                    val selectedGender = selectedRadioButton.text
                    currentGender.value = selectedGender
                }
            }
    }


    private fun addIncidentTypeDropDown() {
        val dropdownMenu = Menu("Select Type of Incident")
        dropdownMenu.items.addAll(
            MenuItem("Unlawful Entry"),
            MenuItem("Property Damage"),
            MenuItem("Stealing From Dwellings"),
            MenuItem("Vehicle theft"),
            MenuItem("Assault"),
            MenuItem("Sexual Offense"),
            MenuItem("Others")
        )
        incidentType.promptText = dropdownMenu.text
        dropdownMenu.onAction = EventHandler { event: ActionEvent ->
            val selectedItem = event.target as MenuItem
            incidentType.text = selectedItem.text
        }
        val menuBar = MenuBar()
        menuBar.menus.add(dropdownMenu)
        incidentParent.children.addAll(incidentType, menuBar)
    }

    private fun addInjuryDropDown() {
        val dropdownMenu = Menu("Select Injury")
        dropdownMenu.items.addAll(
            MenuItem("Yes"),
            MenuItem("No"),
        )
        injury.promptText = dropdownMenu.text
        dropdownMenu.onAction = EventHandler { event: ActionEvent ->
            val selectedItem = event.target as MenuItem
            injury.text = selectedItem.text
        }
        val menuBar = MenuBar()
        menuBar.menus.add(dropdownMenu)
        injuryParent.children.addAll(injury, menuBar)
    }


    private fun addIncidentInformation() {
        if (incidentTime.text.isNotEmpty() &&
            incidentType.text.isNotEmpty() &&
            subrubIncidentOccurred.text.isNotEmpty() &&
            streetIncidentOccurred.text.isNotEmpty() &&
            ageCategorySuspect.text.isNotEmpty() &&
            currentGender.value.isNotEmpty() &&
            noOfSuspectInvolved.text.isNotEmpty() &&
            damage.text.isNotEmpty() &&
            estimateLoss.text.isNotEmpty() &&
            injury.text.isNotEmpty() &&
            detailOfInjury.text.isNotEmpty() &&
            residentWhoReported.text.isNotEmpty()
        ) {
            appendDataToCsvFile(
                CsvDataModel(
                    incidentTime = incidentTime.text,
                    incidentType = incidentType.text,
                    suburbName = subrubIncidentOccurred.text,
                    streetIncidentOccurred = streetIncidentOccurred.text,
                    ageCategoryOfSuspect = ageCategorySuspect.text,
                    suspectedGender = currentGender.value,
                    damage = damage.text,
                    estimateLoss = estimateLoss.text,
                    injury = injury.text,
                    injuryDetail = detailOfInjury.text,
                    residentWhoReportedIncident = residentWhoReported.text
                )
            ) {
                incidentType.text = ""
                incidentTime.text = ""
                subrubIncidentOccurred.text = ""
                streetIncidentOccurred.text = ""
                ageCategorySuspect.text = ""
                noOfSuspectInvolved.text = ""
                damage.text = ""
                estimateLoss.text = ""
                injury.text = ""
                detailOfInjury.text = ""
                residentWhoReported.text = ""
                showDialog()
            }
        } else
            showDialog("Please fill all the details..!")
    }
}