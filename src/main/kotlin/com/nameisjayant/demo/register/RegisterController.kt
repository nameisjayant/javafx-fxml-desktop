package com.nameisjayant.demo.register

import com.nameisjayant.demo.utils.Path
import com.nameisjayant.demo.utils.loadScreen
import javafx.beans.property.SimpleIntegerProperty
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.Background
import javafx.scene.paint.Color

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
    private lateinit var residentToggle: Button

    @FXML
    private lateinit var staffToggle: Button

    @FXML
    private fun initialize() {
        if (selected.value == 0) {
            residentToggle.style = "-fx-background-color: #e2b3c"
            residentToggle.textFill = Color.WHITE
            residentToggle.background = Background.fill(Color.BLACK)
        }
        staffToggle.textFill = Color.BLACK
        staffToggle.background = Background.EMPTY
    }
    @FXML
    fun residentClick(event: Event) {
        selected = SimpleIntegerProperty(0)
        if (selected.value == 0) {
            residentToggle.textFill = Color.WHITE
            residentToggle.background = Background.fill(Color.BLACK)
        }

        staffToggle.textFill = Color.BLACK
        staffToggle.background = Background.EMPTY
    }

    @FXML
    fun administratorClick(event: Event) {
        selected = SimpleIntegerProperty(1)
        if (selected.value == 1) {
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

    @FXML
    private fun register(){

    }
}