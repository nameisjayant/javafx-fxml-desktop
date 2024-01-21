package com.nameisjayant.demo.data.model

data class CsvDataModel(
    val residentId: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val suburbName: String = "",
    val age: String = "",
    val noYearsLived: String = "",
    val phone: String = "",
    val password: String = "",
    val gender: String = "",
    val cityCouncilName: String="",
    val incidentTime: String = "",
    val incidentType: String = "",
    val suburbIncidentOccurred: String = "",
    val streetIncidentOccurred: String = "",
    val ageCategoryOfSuspect: String = "",
    val suspectedGender: String = "",
    val suspectNumbers: String = "",
    val damage: String = "",
    val estimateLoss: String = "",
    val injury: String = "",
    val residentWhoReportedIncident: String = ""
)
