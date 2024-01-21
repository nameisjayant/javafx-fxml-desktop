package com.nameisjayant.demo.utils

import com.nameisjayant.demo.data.model.CsvDataModel
import javafx.event.Event
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.stage.Stage
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Paths
import java.util.*
import kotlin.collections.HashMap

val CSV_FILE_PATH = System.getProperty("user.home") + "/Desktop/data.csv"
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

const val CSV_HEADER =
    "ResidentId, UserType ,FirstName, LastName, Email, Password, Age, Gender, Phone, Subrub Name, City Council Name, Incident Time, Incident Type, Suburb Incident Occurred, Street Incident Occurred, Age Category Of Suspect, Number of Suspect, Damage, Suspect Gender, Injury, Injury Detail, Estimate Loss(in dollars), Resident Who Reported Incident, Incident Type, No of Years Lived"

fun appendDataToCsvFile(dataModel: CsvDataModel, move: () -> Unit) {
    try {
        FileWriter(CSV_FILE_PATH, true).use { writer ->
            writer
                .append(dataModel.residentId).append(",")
                .append(dataModel.userType).append(",")
                .append(dataModel.firstName).append(",")
                .append(dataModel.lastName).append(",")
                .append(dataModel.email).append(",")
                .append(dataModel.password).append(",")
                .append(dataModel.age).append(",")
                .append(dataModel.gender).append(",")
                .append(dataModel.phone).append(",")
                .append(dataModel.suburbName).append(",")
                .append(dataModel.cityCouncilName).append(",")
                .append(dataModel.incidentTime).append(",")
                .append(dataModel.incidentType).append(",")
                .append(dataModel.suburbIncidentOccurred).append(",")
                .append(dataModel.streetIncidentOccurred).append(",")
                .append(dataModel.ageCategoryOfSuspect).append(",")
                .append(dataModel.suspectNumbers).append(",")
                .append(dataModel.damage).append(",")
                .append(dataModel.suspectedGender).append(",")
                .append(dataModel.injury).append(",")
                .append(dataModel.injuryDetail).append(",")
                .append(dataModel.estimateLoss).append(",")
                .append(dataModel.residentWhoReportedIncident).append(",")
                .append(dataModel.incidentType).append(",")
                .append(dataModel.noYearsLived)
                .append("\n")
        }
        move()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}


fun showDialog(
    description: String = "Incident Information is Added...!"
) {
    val alert = Alert(Alert.AlertType.INFORMATION)
    alert.title = "Message"
    alert.contentText = description
    alert.showAndWait()
}


fun setPref(key: String, value: String) = PreferenceStore.preferences.put(key, value)


fun getPref(key: String): String = PreferenceStore.preferences.get(key, "0")


fun createCsvFileIfNotExists() {
    try {
        val csvFile = File(CSV_FILE_PATH)
        if (!csvFile.exists()) {
            csvFile.createNewFile()
            FileWriter(csvFile).use { writer ->
                writer.append(CSV_HEADER)
                writer.append("\n")
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun loadUserDataFromCsvFile(): MutableList<Map<String, String>?> {
    val userDataList: MutableList<Map<String, String>?> = mutableListOf()
    try {
        Scanner(File(CSV_FILE_PATH)).use { scanner ->
            while (scanner.hasNextLine()) {
                val line: String = scanner.nextLine()
                val data =
                    line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (data.size >= 2) {
                    val userData: MutableMap<String, String> =
                        HashMap()
                    userData["ResidentId"] = data[0]
                    userData["userType"] = data[1]
                    userData["FirstName"] = data[2]
                    userData["LastName"] = data[3]
                    userData["Email"] = data[4]
                    userData["Password"] = data[5]
                    userData["Age"] = data[6]
                    userData["Gender"] = data[7]
                    userData["Phone"] = data[8]
                    userData["SubrubName"] = data[9]
                    userData["CityCouncilName"] = data[10]
                    userData["IncidentTime"] = data[11]
                    userData["IncidentType"] = data[12]
                    userData["SuburbIncidentOccurred"] = data[13]
                    userData["StreetIncidentOccurred"] = data[14]
                    userData["AgeCategoryOfSuspect"] = data[15]
                    userData["NumberOfSuspect"] = data[16]
                    userData["Damage"] = data[17]
                    userData["SuspectGender"] = data[18]
                    userData["Injury"] = data[19]
                    userData["InjuryDetail"] = data[20]
                    userData["EstimateLoss"] = data[21]
                    userData["ResidentWhoReportedIncident"] = data[22]
                    userData["IncidentType"] = data[23]
                    userData["NoOfYearsLived"] = data[24]
                    userDataList.add(userData)

                }
            }
        }
        return userDataList
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return userDataList
}