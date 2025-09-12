package com.hoamz.jpyt

import android.content.ContentValues.TAG
import android.credentials.ClearCredentialStateException
import android.credentials.GetCredentialException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hoamz.jpyt.auth.AuthState
import com.hoamz.jpyt.auth.AuthViewModel
import com.hoamz.jpyt.auth.ItemViewModel
import com.hoamz.jpyt.auth.LoginScreen
import com.hoamz.jpyt.auth.NestedLazy
import com.hoamz.jpyt.screens.Route
import com.hoamz.jpyt.screens.SplashScreen
import com.hoamz.jpyt.ui.theme.JPYTTheme
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : ComponentActivity() {
    /*
    //nho chay 2 dong nay khi auth by gg
    ./gradlew clean
    ./gradlew app:processDebugGoogleServices
     */
    private lateinit var auth : FirebaseAuth
    private val authViewModel: AuthViewModel by viewModels<AuthViewModel>()
    private val itemViewModel : ItemViewModel by viewModels<ItemViewModel>()
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
                    SplashScreen()
                }
            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Composable
    fun Login() {
        val navController = rememberNavController()
        val context = LocalContext.current
        val authState by authViewModel.authState.collectAsState()
        LaunchedEffect(keys = arrayOf(authState)) {
            //set tung case o day
            when(authState){
                AuthState.LOGGED_IN ->{
                    navController.navigate(Route.HomeScreen.route){
                        popUpTo(Route.LoginScreen.route){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
                AuthState.LOGGED_OUT -> {
                    navController.navigate(Route.LoginScreen.route){
                        popUpTo(Route.HomeScreen.route){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }

        Scaffold {innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {

                NavHost(
                    navController = navController,
                    startDestination = Route.LoginScreen.route
                ){
                    composable(route = Route.LoginScreen.route){
                        LoginScreen{
                            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
                            launchCredentialManager()
                        }
                    }
                    composable(route = Route.HomeScreen.route){

                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun launchCredentialManager() {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.default_web_client_id))
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                Log.e("Login", "Launching Credential Manager...")
                val result = credentialManager.getCredential(
                    context = this@MainActivity,
                    request = request
                )
                Log.e("Login", "Got result: ${result.credential}")
                handlerSignIn(result.credential)
            } catch (e: Exception) {
                Log.e("Login", "Couldn't retrieve user's credentials: ${e.message}", e)
            }
        }
    }

    private fun handlerSignIn(credential: Credential) {
        if(credential is CustomCredential
            && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
            //tao google id token
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            //login
            authViewModel.signInWithGoogle(idToken = googleIdTokenCredential.idToken)
        }
    }
    //sign out



}
