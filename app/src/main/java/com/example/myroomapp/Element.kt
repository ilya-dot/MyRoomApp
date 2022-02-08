package com.example.myroomapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
data class Element(
    var title: String = "",
    var body: String = "",
    var date: Date = Date()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}