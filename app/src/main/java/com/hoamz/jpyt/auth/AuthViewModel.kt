package com.hoamz.jpyt.auth

import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.hoamz.jpyt.screens.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception


enum class AuthState{
    LOGGED_IN,
    LOGGED_OUT
}

class AuthViewModel : ViewModel(){
    private val _authState = MutableStateFlow(AuthState.LOGGED_OUT)
    val authState : StateFlow<AuthState> = _authState

    private var auth = Firebase.auth

    fun checkCurrentUser(){
        _authState.value = if(auth.currentUser != null) AuthState.LOGGED_IN else AuthState.LOGGED_OUT
    }

    fun signInWithGoogle(idToken : String){
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.LOGGED_IN
                }
            }
    }

    fun signOut(credentialManager: CredentialManager){
        auth.signOut()
        _authState.value = AuthState.LOGGED_OUT
        viewModelScope.launch {
            val clearRq = ClearCredentialStateRequest()
            credentialManager.clearCredentialState(clearRq)
        }
    }
}


sealed class UiState<out T>{
    object Loading : UiState<Nothing>()
    object Retrying : UiState<Nothing>()
    object SwipeRefreshing : UiState<Nothing>()
    data class Success<T>(val data : T) : UiState<T>()
    data class Failure(val exception: Exception) : UiState<Nothing>()
}

