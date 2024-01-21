package com.nameisjayant.demo

import com.nameisjayant.demo.utils.PreferenceStore
import com.nameisjayant.demo.utils.loadScreen
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.File
import java.io.IOException
import java.util.prefs.Preferences

val CSV_FILE_PATH = System.getProperty("user.home") + "/Desktop/data.csv"

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val preferences = Preferences.userNodeForPackage(javaClass)
        PreferenceStore.setPreference(preferences)
        createCsvFileIfNotExists()
        stage.title = "NCIRS App"
        when (PreferenceStore.preferences.get(PreferenceStore.index, "0")) {
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

private fun createCsvFileIfNotExists() {
    try {
        val csvFile = File(CSV_FILE_PATH)
        if (!csvFile.exists()) {
            csvFile.createNewFile()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}