package com.hoamz.jpyt.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hoamz.jpyt.ui.screens.DetailScreen
import com.hoamz.jpyt.ui.screens.HomeScreen
import com.hoamz.jpyt.viewmodel.MainViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    NavHost(
        navController =  navController,
        startDestination = Screen.HomeScreen.route)
    {
        composable(route = Screen.HomeScreen.route){
            HomeScreen(onClickSaveUser = {user ->
                mainViewModel.insertUser(user)
            }) {
                navController.navigate(route = Screen.DetailScreen.route)
            }
        }
        composable(route = Screen.DetailScreen.route){
            val uses = mainViewModel.users.collectAsState().value
            DetailScreen(uses){user ->
                //edit
                mainViewModel.updateUser(user)
            }
        }
    }
}