package com.hoamz.jpyt.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val idUser : Long? = null,
    val username : String
)