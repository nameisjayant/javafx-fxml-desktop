package com.nameisjayant.demo.start

import com.nameisjayant.demo.utils.Path
import com.nameisjayant.demo.utils.loadScreen
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight


class StartController {

    @FXML
    private lateinit var title: Label

    @FXML
    private fun login(event: Event) {
        loadScreen(Path.LOGIN_PATH, event)
    }

    @FXML
    private fun register(event: Event) {
        loadScreen(Path.REGISTER_PATH, event)
    }

    private fun setTitleFont() {
        val font: Font = Font.font("Arial", FontWeight.BOLD, 35.0)
        title.font = font
        title.textFill = Color.BLUE
    }


}