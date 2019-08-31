package com.android.freelance.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.freelance.mvvm.data.db.entities.Current_UserId
import com.android.freelance.mvvm.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User): Long

    @Query("select * from user nolock where id = $Current_UserId")
    fun getUser() : LiveData<User>
}