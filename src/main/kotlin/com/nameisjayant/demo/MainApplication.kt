package com.nameisjayant.demo

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.FileWriter
import java.io.IOException


class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("start-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 600.0, 400.0)
        generateCsv()
        stage.title = "NCIRS App"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}

private fun generateCsv() {
    val fileChooser = FileChooser()
    fileChooser.title = "Save CSV File"
    fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("CSV Files", "*.csv"))

    val selectedFile = fileChooser.showSaveDialog(null)

    if (selectedFile != null) {
        try {
            FileWriter(selectedFile).use { writer ->
                // Writing CSV content
                writer.append("Name, Age, Occupation\n")
                writer.append("John Doe, 30, Engineer\n")
                writer.append("Jane Smith, 25, Teacher\n")
                writer.flush()
                println("CSV file created successfully.")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}