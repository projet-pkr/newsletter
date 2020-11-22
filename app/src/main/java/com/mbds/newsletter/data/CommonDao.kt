package com.mbds.newsletter.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface CommonDao <T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert( entities: List<T>)

    @Update
    suspend fun update( entity : T)

    @Delete
    suspend fun deleteOne(entity : T)

    @Delete
    suspend fun deleteMultiple(vararg entities : T)
}