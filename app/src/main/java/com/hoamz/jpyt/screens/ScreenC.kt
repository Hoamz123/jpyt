package com.hoamz.jpyt.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hoamz.jpyt.R


@Composable
fun ScreenC(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    onBackPress :(String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().statusBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "C",
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
            Text(text = "Back to Screen A")
        }
    }

    //neu back ve man hinh B thi gui loi hoi tham
    BackHandler {
        onBackPress("hoamz")
    }

}
