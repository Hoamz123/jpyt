package com.hoamz.jpyt.data.mapper

import com.hoamz.jpyt.data.local.UserEntity
import com.hoamz.jpyt.domain.model.User

object UserMapper {
    fun entityToUser(userEntity: UserEntity) : User {
        return User(idUser = userEntity.idUser,username = userEntity.username)
    }
    fun userToEntity(user: User) : UserEntity {
        return UserEntity(username = user.username)
    }
}