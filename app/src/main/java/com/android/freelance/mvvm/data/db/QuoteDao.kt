package com.android.freelance.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.freelance.mvvm.data.db.entities.Quotes

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //if it happened in duplicate "id" which is replace for one record.
    fun saveAllQuotes(quotes: List<Quotes>)

    @Query("select * from Quotes nolock;")
    fun getQuotes(): LiveData<List<Quotes>>

}