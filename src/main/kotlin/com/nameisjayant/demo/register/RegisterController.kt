package com.nameisjayant.demo.register

import com.nameisjayant.demo.utils.Path
import com.nameisjayant.demo.utils.loadScreen
import javafx.beans.property.SimpleIntegerProperty
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup

class RegisterController {

    @FXML
    private lateinit var firstname:TextField

    @FXML
    private lateinit var lastName:TextField
    @FXML
    private lateinit var email:TextField
    @FXML
    private lateinit var subrubName:TextField
    @FXML
    private lateinit var age:TextField
    @FXML
    private lateinit var yearsLived:TextField
    @FXML
    private lateinit var phone:TextField
    @FXML
    private lateinit var password:TextField


    private var selected = SimpleIntegerProperty(0)

    @FXML
    private lateinit var residentToggle: ToggleButton

    @FXML
    private lateinit var staffToggle: ToggleButton
    private lateinit var toggleGroup: ToggleGroup
    @FXML
    fun residentClick(event: Event) {

        selected = SimpleIntegerProperty(0)
    }

    @FXML
    private fun initialize() {
        toggleGroup = ToggleGroup()

        residentToggle.toggleGroup = toggleGroup
        staffToggle.toggleGroup = toggleGroup


        toggleGroup.selectToggle(residentToggle)
    }

    @FXML
    fun administratorClick(event: Event) {
        selected = SimpleIntegerProperty(1)
    }

    @FXML
    fun back(event: Event) {
        loadScreen(Path.HOME_PATH, event)
    }

    @FXML
    private fun register(){

    }
}