package com.example.myroomapp

import androidx.room.*

interface ElementDao {
    @Query("SELECT * from Element")
    suspend fun getAll():List<Element>

    @Query("SELECT * from Element WHERE id = :id")
    suspend fun getById(id: Int): Element?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(element: Element):Long

    @Update
    suspend fun update(element: Element)

    @Query("DELETE FROM Element WHERE id = :id")
    suspend fun deleteById(id: Int)
}
