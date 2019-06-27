package com.podium.app.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.kautilyatask.Model.ItemModel
import java.util.*


@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upateItems(kanban:List<ItemModel>)

    @Query("Select * from ItemModel ")
    fun getItems() : LiveData<List<ItemModel>>


    @Query("Update itemModel SET fav = :fav where id =:id ")
    fun setFav(fav:String,id:String)

    @Query("Update itemModel SET title = :title where id =:id ")
    fun setTitle(title:String,id:String)


}
