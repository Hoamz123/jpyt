package com.hoamz.jpyt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg userEntity: UserEntity)

    //get All
    @Query("select * from userentity")
    fun getAllUser() : Flow<List<UserEntity>>

    //update
    @Update
    suspend fun updateUser(userEntity: UserEntity)
}