package com.hoamz.jpyt

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.credentials.CredentialManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hoamz.jpyt.ui.theme.JPYTTheme
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : ComponentActivity() {
    /*
    //nho chay 2 dong nay khi auth by gg
    ./gradlew clean
    ./gradlew app:processDebugGoogleServices
     */
    private lateinit var auth: FirebaseAuth

    private lateinit var credentialManager: CredentialManager

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        credentialManager = CredentialManager.create(this)
        setContent {
            JPYTTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                }
            }
        }
    }
}


