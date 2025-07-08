package com.example.composekmpapp.shared.session

import com.example.composekmpapp.shared.data.Location
import com.example.composekmpapp.shared.data.Shift

object SessionManager {
    var currentUserName: String? = "Current User" // Placeholder, could be set after login
    var currentShift: Shift? = null
    var currentLocation: Location? = null
    var currentUserPosition: String? = "Security Officer" // Placeholder

    fun startSession(userName: String, shift: Shift, location: Location, position: String = "Security Officer") {
        currentUserName = userName
        currentShift = shift
        currentLocation = location
        currentUserPosition = position
    }

    fun clearSession() {
        currentUserName = null
        currentShift = null
        currentLocation = null
        currentUserPosition = null
    }

    val isLoggedIn: Boolean
        get() = currentUserName != null && currentShift != null && currentLocation != null
}
