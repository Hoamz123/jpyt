package com.hoamz.jpyt.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.ScreenA.route,
    ){
        composable(route = Route.ScreenA.route) {
            ScreenA {username ->
                navController.navigate(Route.ScreenB.passDataToScreenB(username))
            }
        }

        composable(route = Route.ScreenB.route, arguments = listOf(
            navArgument(SCREEN_B){
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            }
        )) {backStackEntry ->
            val username = backStackEntry.arguments?.getString(SCREEN_B) ?: "B"
            ScreenB(
                username = username,
                navController = navController
            ) {
                navController.navigate(Route.ScreenC.route)
            }
        }

        composable(route = Route.ScreenC.route) {
            ScreenC (onClick = {
                navController.navigate(Route.ScreenA.route){
                    //bo C ra khoi stack
                    popUpTo(Route.ScreenC.route){
                        inclusive = true
                    }
                    //bo B rac khoi tack
                    popUpTo(Route.ScreenB.route){
                        inclusive = true
                    }
                    //- > muc dich la de man hinh A coi nhu l man hinh Home (ko back dc di dau nua -> out app)
                    launchSingleTop = true
                }
            }, onBackPress = {nameAmin ->
                navController.previousBackStackEntry?.savedStateHandle
                    ?.set("NAME_ADMIN",nameAmin)
                //pop ve B
                navController.popBackStack()
            })
        }
    }
}
