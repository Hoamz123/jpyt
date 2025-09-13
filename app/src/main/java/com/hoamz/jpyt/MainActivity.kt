package com.hoamz.jpyt

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hoamz.jpyt.ui.theme.JPYTTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : ComponentActivity() {
    /*
    //nho chay 2 dong nay khi auth by gg
    ./gradlew clean
    ./gradlew app:processDebugGoogleServices
     */
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JPYTTheme {
                val scrollState = rememberScrollState()
//                Log.e("scrollState","${scrollState.value}")
                val isScrolled by remember {
                    derivedStateOf {
                        scrollState.value == 0
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Scaffold(
                        topBar = {
                            //cuong nen -> an top bar
                            AnimatedVisibility(
                                visible = isScrolled,//neu = 0 thi chien thi <=> voi khong cuong
                                exit = slideOutVertically() + fadeOut(),
                                enter = slideInHorizontally()
                            ) {
                                TopAppBar(
                                    title = {Text(text = "Home")},
                                    navigationIcon = {
                                        Icon(Icons.Default.Menu,
                                            contentDescription = null,
                                            modifier = Modifier.padding(start = 16.dp))
                                    },
                                    colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = Color.Magenta
                                    )
                                )
                            }
                        }
                    ) {innerPadding ->
                        Column(
                            modifier = Modifier.padding(innerPadding)
                                .fillMaxSize()
                                .verticalScroll(state = scrollState),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            repeat(5){index ->
                                Box (
                                    modifier = Modifier.size(300.dp).fillMaxWidth()
                                        .background(if(index % 2 == 1) Color.Red else Color.Blue),
                                    contentAlignment = Alignment.Center
                                ){}
                            }
                        }
                    }
                }
            }
        }
    }
}


