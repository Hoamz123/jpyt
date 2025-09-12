package com.hoamz.jpyt.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hoamz.jpyt.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    itemViewModel: ItemViewModel,
    navController: NavController
) {
    Column(
        modifier = modifier.fillMaxSize().statusBarsPadding()
            .background(color = Color.White).padding(5.dp),

    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
            items(5){index ->
                CardItem(username = "gia su username$index",
                    itemViewModel = itemViewModel
                    ){
                    navController.navigate("DetailScreen")
                }
            }
        }
    }
}


@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    username : String,
    itemViewModel: ItemViewModel,
    onClick :() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp).clickable{
                itemViewModel.setUsername(username = username)
                onClick()
            }.clip(RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(
            focusedElevation = 10.dp,
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Magenta
        )
    ) {
        Box(
            modifier = Modifier
                .height(50.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = username,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
