package com.nameisjayant.demo.utils

import com.nameisjayant.demo.CSV_FILE_PATH
import com.nameisjayant.demo.data.model.CsvDataModel
import javafx.event.Event
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Paths


fun loadScreen(fxml: String, event: Event, store: () -> Unit = {}) {
    try {
        store()
        val path = Paths.get(fxml).toUri().toURL()
        val root: Parent = FXMLLoader.load(path)
        val stage = (event.source as Node).scene.window as Stage
        stage.scene = Scene(root)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun appendDataToCsvFile(dataModel: CsvDataModel, move: () -> Unit) {
    try {
        FileWriter(CSV_FILE_PATH, true).use { writer ->
            writer.append(dataModel.firstName).append(",")
                .append(dataModel.lastName).append(",")
                .append(dataModel.email).append(",")
                .append(dataModel.password).append(",")
                .append(dataModel.age).append(",")
                .append(dataModel.gender).append(",")
                .append(dataModel.phone).append(",")
                .append(dataModel.suburbName).append(",")
                .append(dataModel.noYearsLived)
                .append("\n")
        }
        move()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}