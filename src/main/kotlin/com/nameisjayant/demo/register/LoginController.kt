package com.nameisjayant.demo.register

import com.nameisjayant.demo.utils.*
import javafx.beans.property.SimpleStringProperty
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.Background
import javafx.scene.paint.Color


class LoginController {

    @FXML
    private lateinit var email: TextField

    @FXML
    private lateinit var password: PasswordField
    private var selected = SimpleStringProperty(RESIDENT_TYPE)

    @FXML
    private lateinit var residentToggle: Button

    @FXML
    private lateinit var staffToggle: Button
    private var userDataList: MutableList<Map<String, String>?> = mutableListOf()


    @FXML
    private fun initialize() {
        if (selected.value == RESIDENT_TYPE) {
            residentToggle.textFill = Color.WHITE
            residentToggle.background = Background.fill(Color.BLACK)
        }
        staffToggle.textFill = Color.BLACK
        staffToggle.background = Background.EMPTY
        userDataList = loadUserDataFromCsvFile()
    }

    @FXML
    fun login(event: Event) {
        if (userDataList.isNotEmpty() && email.text.isNotEmpty() && password.text.isNotEmpty()) {
            val isContain = userDataList.any {
                it?.let {
                    it.containsValue(email.text)
                            && it.containsValue(password.text)
                            && it.containsValue(selected.value)
                } == true
            }
            if (isContain)
                showDialog("Logged Successful")
            else
                showDialog("Wrong Email and Password")
        }
    }

    @FXML
    fun residentClick(event: Event) {
        selected.value = RESIDENT_TYPE
        if (selected.value == RESIDENT_TYPE) {
            residentToggle.textFill = Color.WHITE
            residentToggle.background = Background.fill(Color.BLACK)
        }

        staffToggle.textFill = Color.BLACK
        staffToggle.background = Background.EMPTY
    }

    @FXML
    fun administratorClick(event: Event) {
        selected.value = ADMINISTRATION_TYPE
        if (selected.value == ADMINISTRATION_TYPE) {
            staffToggle.textFill = Color.WHITE
            staffToggle.background = Background.fill(Color.BLACK)
        }
        residentToggle.textFill = Color.BLACK
        residentToggle.background = Background.EMPTY

    }

    @FXML
    fun back(event: Event) {
        loadScreen(Path.HOME_PATH, event)
    }
}