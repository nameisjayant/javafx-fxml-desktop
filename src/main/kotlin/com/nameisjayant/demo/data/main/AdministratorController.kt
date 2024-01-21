package com.nameisjayant.demo.data.main

import com.nameisjayant.demo.utils.Path
import com.nameisjayant.demo.utils.PreferenceStore
import com.nameisjayant.demo.utils.loadScreen
import com.nameisjayant.demo.utils.setPref
import javafx.event.Event
import javafx.fxml.FXML

class AdministratorController {

    @FXML
    private fun logout(event: Event) {
        loadScreen(Path.HOME_PATH, event) {
            setPref(PreferenceStore.index, "0")
        }
    }

}