package com.nameisjayant.demo.register

import com.nameisjayant.demo.utils.Path
import com.nameisjayant.demo.utils.loadScreen
import javafx.beans.property.SimpleIntegerProperty
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup

class LoginController {

    @FXML
    private lateinit var username: TextField

    @FXML
    private lateinit var password: PasswordField
    private var selected = SimpleIntegerProperty(0)

    @FXML
    private lateinit var residentToggle: ToggleButton

    @FXML
    private lateinit var staffToggle: ToggleButton
    private lateinit var toggleGroup: ToggleGroup

    @FXML
    private fun initialize() {
        toggleGroup = ToggleGroup()

        residentToggle.toggleGroup = toggleGroup
        staffToggle.toggleGroup = toggleGroup


        toggleGroup.selectToggle(residentToggle)
        println(selected.value)
    }

    @FXML
    fun login(event: Event) {

    }

    @FXML
    fun residentClick(event: Event) {

        selected = SimpleIntegerProperty(0)
    }

    @FXML
    fun administratorClick(event: Event) {
        selected = SimpleIntegerProperty(1)
    }

    @FXML
    fun back(event: Event) {
        loadScreen(Path.HOME_PATH, event)
    }

}