package com.hoamz.jpyt

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.tooling.LocalInspectionTables
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun CustomAnimation(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //false : an // true  : hien thi
        var toggle by remember {
            mutableStateOf(false)
        }
        //visibility
        Button(
            onClick = {
                toggle = !toggle
            }
        ) {
            Text("Show")
        }
//        AnimatedVisibility(visible = toggle, exit = slideOutHorizontally() + fadeOut()) {
//            ShowLottie()
//        }

        //Shape
        val transition = updateTransition(targetState = toggle)
        val cornerRadius by transition.animateInt(
            transitionSpec = {tween(durationMillis = 3000)},
            targetValueByState = {isRound ->
                if(isRound) 180 else 0
            }
        )
        val color by transition.animateColor(
            transitionSpec = {tween(durationMillis = 1000)},
            targetValueByState = {isRound ->
                if(isRound) Color.Red else colorResource(R.color.purple_200)
            }
        )
        Box(
            modifier = Modifier.size(300.dp)
                .clip(RoundedCornerShape(cornerRadius.dp))
                .background(color = color)
        ){}
    }
}


@Composable
fun ShowLottie() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("Loading.json")
    )
    Box (
        modifier = Modifier.size(300.dp),
        contentAlignment = Alignment.Center
    ){
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
}