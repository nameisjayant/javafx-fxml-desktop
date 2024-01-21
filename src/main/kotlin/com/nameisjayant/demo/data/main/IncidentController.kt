package com.nameisjayant.demo.data.main

import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.event.EventHandler
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
    private fun logout() {
    }

    @FXML
    private fun addIncidentDetail() {
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

}