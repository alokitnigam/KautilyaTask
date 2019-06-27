package com.podium.app.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.kautilyatask.Model.ItemModel
import java.util.*


@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upateItems(kanban:ItemModel)

    @Query("Select * from ItemModel ")
    fun getItems() : List<ItemModel>



}
