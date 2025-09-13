package com.hoamz.jpyt

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
        val list = listOf(
            Item("Home", Icons.Default.Home),
            Item("Share", Icons.Default.Share),
            Item("Settings", Icons.Default.Settings)
        )
        setContent {
            JPYTTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavDrawer(listItem = list)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(
    modifier: Modifier = Modifier,
    listItem : List<Item>
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)
                        .height(180.dp)
                ){
                    Image(
                        painter = painterResource(R.drawable.banner2),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth
                    )
                }

                listItem.forEach { item ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(start = 16.dp),
                        label = { Text(text = item.title) },
                        onClick = {

                        },
                        selected = false,
                        icon = {
                            Icon(imageVector = item.icon,contentDescription = null)
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text(text = "Menu", modifier = Modifier.padding(20.dp))},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu,contentDescription = null)
                        }
                    }
                )
            }
        ) {innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Home")
            }
        }
    }
}


data class Item(
    val title : String,
    val icon : ImageVector
)




