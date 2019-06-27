package com.podium.app.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.kautilyatask.Model.ItemModel
import java.util.*


@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItems(item :List<ItemModel>)

    @Query("Select * from ItemModel ")
    fun getItems() : LiveData<List<ItemModel>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItem(item : ItemModel)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun setTitle(item : ItemModel)

    @Query("Select * from ItemModel where fav='1'")
    fun getFavCount() : LiveData<List<ItemModel>>


}
