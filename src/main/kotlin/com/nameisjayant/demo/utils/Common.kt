package com.nameisjayant.demo.utils

import javafx.event.Event
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.IOException
import java.nio.file.Paths


fun loadScreen(fxml: String, event: Event) {
    try {
        val path = Paths.get(fxml).toUri().toURL()
        val root: Parent = FXMLLoader.load(path)
        val stage = (event.source as Node).scene.window as Stage
        stage.scene = Scene(root)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}