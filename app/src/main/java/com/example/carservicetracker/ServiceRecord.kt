package com.example.carservicetracker

// Data class representing one service entry
data class ServiceRecord(
    val serviceType: String,
    val serviceDate: String,
    val notes: String
) {
    override fun toString(): String {
        return "Service: $serviceType\nDate: $serviceDate\nNotes: $notes"
    }

    // Convert record to string for saving
    fun serialize(): String {
        return "$serviceType|$serviceDate|$notes"
    }

    companion object {
        // Convert saved string back into ServiceRecord
        fun deserialize(data: String): ServiceRecord {
            val parts = data.split("|")
            return ServiceRecord(parts[0], parts[1], parts[2])
        }
    }
}
