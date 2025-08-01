package com.example.carservicetracker

import android.content.Context
import java.io.*

object StorageUtil {
    private const val FILENAME = "car_services.txt"

    // Save new record to internal storage
    fun saveRecord(context: Context, record: ServiceRecord) {
        try {
            context.openFileOutput(FILENAME, Context.MODE_APPEND).use { fos ->
                fos.write((record.serialize() + "\n").toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Load all records
    fun loadRecords(context: Context): List<ServiceRecord> {
        val records = mutableListOf<ServiceRecord>()
        try {
            context.openFileInput(FILENAME).bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    records.add(ServiceRecord.deserialize(line))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return records
    }
}
