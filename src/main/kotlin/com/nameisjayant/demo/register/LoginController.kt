package com.nameisjayant.demo.register

import com.nameisjayant.demo.CSV_FILE_PATH
import com.nameisjayant.demo.utils.Path
import com.nameisjayant.demo.utils.PreferenceStore
import com.nameisjayant.demo.utils.loadScreen
import javafx.beans.property.SimpleIntegerProperty
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.Background
import javafx.scene.paint.Color
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap


class LoginController {

    @FXML
    private lateinit var username: TextField

    @FXML
    private lateinit var password: PasswordField
    private var selected = SimpleIntegerProperty(0)

    @FXML
    private lateinit var residentToggle: Button

    @FXML
    private lateinit var staffToggle: Button
    private val userDataList: MutableList<Map<String, String>> = mutableListOf()


    @FXML
    private fun initialize() {
        if (selected.value == 0) {
            residentToggle.style = "-fx-background-color: #e2b3c"
            residentToggle.textFill = Color.WHITE
            residentToggle.background = Background.fill(Color.BLACK)
        }
        staffToggle.textFill = Color.BLACK
        staffToggle.background = Background.EMPTY
        loadUserDataFromCsvFile()
        PreferenceStore.preferences.put("name","Jayant")
    }

    @FXML
    fun login(event: Event) {
        //  appendDataToCsvFile("Jayant", "jks123@gmail.com")
        if (userDataList.isNotEmpty()) {
            val isContain = userDataList.any {
                it.containsValue("Manish")
            }
            println(isContain)
        }
        println(PreferenceStore.preferences.get("name","not found"))
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

    private fun loadUserDataFromCsvFile() {
        try {
            Scanner(File(CSV_FILE_PATH)).use { scanner ->
                while (scanner.hasNextLine()) {
                    val line: String = scanner.nextLine()
                    val data =
                        line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    if (data.size >= 2) {
                        val username = data[0]
                        val email = data[1]
                        val userData: MutableMap<String, String> =
                            HashMap()
                        userData["username"] = username
                        userData["email"] = email
                        userDataList.add(userData)

                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}