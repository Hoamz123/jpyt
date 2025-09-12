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
import com.hoamz.jpyt.ui.theme.JPYTTheme

// TODO: 9/12/2025  

class MainActivity : ComponentActivity() {
    /*
    //nho chay 2 dong nay khi auth by gg
    ./gradlew clean
    ./gradlew app:processDebugGoogleServices
     */

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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


