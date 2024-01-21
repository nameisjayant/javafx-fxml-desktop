package com.nameisjayant.demo

import com.nameisjayant.demo.utils.PreferenceStore
import com.nameisjayant.demo.utils.createCsvFileIfNotExists
import com.nameisjayant.demo.utils.getPref
import com.nameisjayant.demo.utils.loadScreen
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.File
import java.io.IOException
import java.util.prefs.Preferences


class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val preferences = Preferences.userNodeForPackage(javaClass)
        PreferenceStore.setPreference(preferences)
        createCsvFileIfNotExists()
        stage.title = "NCIRS App"
        when (getPref(PreferenceStore.index)) {
            "1" -> {
                val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("incident-view.fxml"))
                val scene = Scene(fxmlLoader.load())
                stage.scene = scene
                stage.show()
            }

            else -> {
                val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("start-view.fxml"))
                val scene = Scene(fxmlLoader.load(), 600.0, 400.0)
                stage.scene = scene
                stage.show()
            }
        }
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}

