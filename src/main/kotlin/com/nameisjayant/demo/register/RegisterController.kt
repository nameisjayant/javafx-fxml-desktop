package com.nameisjayant.demo.register

import com.nameisjayant.demo.data.model.CsvDataModel
import com.nameisjayant.demo.utils.*
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.Event
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.Background
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color


class RegisterController {

    @FXML
    private lateinit var firstname: TextField

    @FXML
    private lateinit var lastName: TextField

    @FXML
    private lateinit var email: TextField

    @FXML
    private lateinit var subrubName: TextField

    @FXML
    private lateinit var age: TextField

    @FXML
    private lateinit var yearsLived: TextField

    @FXML
    private lateinit var phone: TextField

    @FXML
    private lateinit var password: TextField

    @FXML
    private lateinit var residentParent: VBox

    @FXML
    private lateinit var staffParent: VBox

    private var selected = SimpleStringProperty(RESIDENT_TYPE)

    @FXML
    private lateinit var residentToggle: Button

    @FXML
    private lateinit var staffToggle: Button
    private val cityCouncilTextField: TextField = TextField()

    @FXML
    private lateinit var radioGroup: HBox
    private val currentGender = SimpleStringProperty()
    private var userDataList: MutableList<Map<String, String>?> = mutableListOf()

    @FXML
    private fun initialize() {
        if (selected.value == RESIDENT_TYPE) {
            residentToggle.textFill = Color.WHITE
            residentToggle.background = Background.fill(Color.BLACK)
        }
        staffToggle.textFill = Color.BLACK
        staffToggle.background = Background.EMPTY
        createRadioGroup()
        userDataList = loadUserDataFromCsvFile()
    }

    @FXML
    fun residentClick(event: Event) {
        selected = SimpleStringProperty(RESIDENT_TYPE)
        if (selected.value == RESIDENT_TYPE) {
            residentToggle.textFill = Color.WHITE
            residentToggle.background = Background.fill(Color.BLACK)
            isVisibleResident(true)
            staffParent.isVisible = false
            // addDropDownTextField(false)
        }
        staffToggle.textFill = Color.BLACK
        staffToggle.background = Background.EMPTY
    }

    @FXML
    fun administratorClick(event: Event) {
        selected = SimpleStringProperty(ADMINISTRATION_TYPE)
        if (selected.value == ADMINISTRATION_TYPE) {
            staffToggle.textFill = Color.WHITE
            staffToggle.background = Background.fill(Color.BLACK)
            isVisibleResident(false)
            // addDropDownTextField(true)
        }
        residentToggle.textFill = Color.BLACK
        residentToggle.background = Background.EMPTY

    }

    @FXML
    fun back(event: Event) {
        loadScreen(Path.HOME_PATH, event)
    }

    @FXML
    private fun register(event: Event) {
        if (selected.value == RESIDENT_TYPE)
            storeResidentData(event)
        else
            storeAdministratorData(event)
    }

    private fun isVisibleResident(isVisible: Boolean) {
        residentParent.isVisible = isVisible
    }

    private fun addDropDownTextField(isVisible: Boolean) {
        val dropdownMenu = Menu("Select City Council")
        dropdownMenu.items.addAll(
            MenuItem("Brisbane"),
            MenuItem("Logan"),
            MenuItem("Ipswich"),
            MenuItem("Redland"),
            MenuItem("Toowoomba"),
            MenuItem("Sunshine Coast"),
            MenuItem("Gold Coast")
        )
        cityCouncilTextField.text = dropdownMenu.text
        dropdownMenu.onAction = EventHandler { event: ActionEvent ->
            val selectedItem = event.target as MenuItem
            cityCouncilTextField.text = selectedItem.text
        }
        val menuBar = MenuBar()
        menuBar.menus.add(dropdownMenu)
        staffParent.children.addAll(cityCouncilTextField, menuBar)
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

    private fun storeAdministratorData(event: Event) {
        if (firstname.text.isNotEmpty() &&
            lastName.text.isNotEmpty() &&
            email.text.isNotEmpty() &&
            password.text.isNotEmpty() &&
            age.text.isNotEmpty() &&
            currentGender.value.isNotEmpty()
        ) {
            val isContain = userDataList.any {
                it?.containsValue(email.text) == true
            }
            val model = CsvDataModel(
                firstName = firstname.text,
                lastName = lastName.text,
                email = email.text,
                password = password.text,
                age = age.text,
                gender = currentGender.value,
                userType = ADMINISTRATION_TYPE
            )
            if (!isContain)
                appendDataToCsvFile(
                    model
                ) {
                    loadScreen(Path.ADMINISTRATOR_PATH, event) {
                        setPref(PreferenceStore.index, "2")
                        setPref(PreferenceStore.userId, model.residentId)
                    }
                } else
                showDialog("Email Already Exits..!")

        } else
            showDialog("Please Fill all the fields..!")
    }

    private fun storeResidentData(event: Event) {
        if (firstname.text.isNotEmpty() &&
            lastName.text.isNotEmpty() &&
            email.text.isNotEmpty() &&
            password.text.isNotEmpty() &&
            age.text.isNotEmpty() &&
            currentGender.value.isNotEmpty() &&
            phone.text.isNotEmpty() &&
            subrubName.text.isNotEmpty() &&
            yearsLived.text.isNotEmpty()
        ) {
            val isContain = userDataList.any {
                it?.containsValue(email.text) == true
            }
            val model = CsvDataModel(
                firstName = firstname.text,
                lastName = lastName.text,
                email = email.text,
                password = password.text,
                age = age.text,
                gender = currentGender.value,
                phone = phone.text,
                suburbName = subrubName.text,
                noYearsLived = yearsLived.text,
                userType = RESIDENT_TYPE
            )
            if (!isContain) {
                appendDataToCsvFile(
                    model
                ) {
                    loadScreen(Path.INCIDENT_PATH, event) {
                        setPref(PreferenceStore.index, "1")
                        setPref(PreferenceStore.userId, model.residentId)
                    }
                }
            } else
                showDialog("Email Already Exits..!")
        } else
            showDialog("Please Fill all the fields..!")
    }
}