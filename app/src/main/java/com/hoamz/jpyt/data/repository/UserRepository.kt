package com.hoamz.jpyt.data.repository

import com.hoamz.jpyt.data.local.UserDao
import com.hoamz.jpyt.data.local.UserEntity
import com.hoamz.jpyt.data.mapper.UserMapper
import com.hoamz.jpyt.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getAllUser() : Flow<List<UserEntity>> {
        return userDao.getAllUser()
    }

    suspend fun insertUser(user: User){
        userDao.insertUser(UserMapper.userToEntity(user))
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(UserMapper.userToEntity(user))
    }
}