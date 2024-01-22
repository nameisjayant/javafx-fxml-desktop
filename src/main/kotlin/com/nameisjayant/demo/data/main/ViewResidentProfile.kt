package com.nameisjayant.demo.data.main

import com.nameisjayant.demo.data.model.CsvDataModel
import com.nameisjayant.demo.utils.*
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Label

class ViewResidentProfile {

    @FXML
    private lateinit var firstname: Label

    @FXML
    private lateinit var lastname: Label


    @FXML
    private lateinit var email: Label


    @FXML
    private lateinit var age: Label


    @FXML
    private lateinit var gender: Label


    private var userDataList: MutableList<Map<String, String>?> = mutableListOf()


    @FXML
    private fun initialize() {
        userDataList = loadUserDataFromCsvFile()
        userDataList.forEach {
            if ((it?.get("ResidentId") ?: "") == getPref(PreferenceStore.userId)) {
                it?.let {
                    firstname.text = "First Name : ${it["FirstName"] ?: ""}"
                    lastname.text = "Last Name : ${it["LastName"] ?: ""}"
                    email.text = "Email : ${it["Email"] ?: ""}"
                    age.text = "Age :  ${it["Age"] ?: ""}"
                    gender.text = "Gender : ${it["Gender"] ?: ""} "
                }
            }
        }
    }

    @FXML
    private fun onBack(event: Event) {
        loadScreen(Path.INCIDENT_PATH,event)
    }

}