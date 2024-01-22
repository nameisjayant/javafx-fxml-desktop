package com.nameisjayant.demo.data.main

import com.nameisjayant.demo.data.model.CsvDataModel
import com.nameisjayant.demo.utils.*
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.ListView


class AdministratorController {

    @FXML
    private lateinit var listView: ListView<CsvDataModel>
    private var userDataList: MutableList<Map<String, String>?> = mutableListOf()


    @FXML
    private fun logout(event: Event) {
        loadScreen(Path.HOME_PATH, event) {
            setPref(PreferenceStore.index, "0")
        }
    }

    @FXML
    fun initialize() {
        userDataList = loadUserDataFromCsvFile()
        val data = mutableListOf<CsvDataModel>()
        userDataList.map {
            if ((it?.get("userType") ?: "") == INCIDENT_TYPE)
                data.add(
                    CsvDataModel(
                        residentId = it?.get("ResidentId") ?: "",
                        firstName = it?.get("FirstName") ?: "",
                        lastName = it?.get("LastName") ?: "",
                        incidentTime = it?.get("IncidentTime") ?: "",
                        incidentType = it?.get("IncidentType") ?: "",
                        suburbIncidentOccurred = it?.get("SuburbIncidentOccurred") ?: "",
                        streetIncidentOccurred = it?.get("StreetIncidentOccurred") ?: "",
                        ageCategoryOfSuspect = it?.get("AgeCategoryOfSuspect") ?: "",
                        suspectNumbers = it?.get("NumberOfSuspect") ?: "",
                        damage = it?.get("Damage") ?: "",
                        suspectedGender = it?.get("SuspectGender") ?: "",
                        injury = it?.get("Injury") ?: "",
                        injuryDetail = it?.get("InjuryDetail") ?: "",
                        estimateLoss = it?.get("EstimateLoss") ?: "",
                        residentWhoReportedIncident = it?.get("ResidentWhoReportedIncident") ?: "",
                        userId = it?.get("userId") ?: ""
                    )
                )
        }
        val res = FXCollections.observableArrayList(
            data
        )

        listView.items = res
    }
}