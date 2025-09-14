package com.hoamz.jpyt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoamz.jpyt.data.local.UserEntity
import com.hoamz.jpyt.data.mapper.UserMapper
import com.hoamz.jpyt.data.repository.UserRepository
import com.hoamz.jpyt.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){

    val users : StateFlow<List<User>> = userRepository.getAllUser().map {userEntities ->
        userEntities.map { UserMapper.entityToUser(it) }
    }.stateIn(viewModelScope, SharingStarted.Lazily,emptyList())


    fun insertUser(user: User){
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }
}