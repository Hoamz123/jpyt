package com.hoamz.jpyt.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hoamz.jpyt.R


@Composable
fun ScreenB(
    modifier: Modifier = Modifier,
    username : String,
    navController: NavController,
    onClick : () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().statusBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = username,
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.purple_200)
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(text = "Next to Screen C")
        }
        Spacer(modifier = Modifier.height(20.dp))

        //nhan du lieu gui ve tu C
        val backStackEntry = navController.currentBackStackEntryAsState()
        val nameAdmin = backStackEntry.value?.savedStateHandle
            ?.getLiveData<String>("NAME_ADMIN")
            ?.observeAsState()?.value ?: ""

        Text(
            text = nameAdmin,
            fontSize = 40.sp
        )
    }
}