package com.hoamz.jpyt.ui.navigation


private const val HOME_SCREEN = "home_screen"
private const val DETAIL_SCREEN = "detail_screen"
sealed class Screen(val route : String){
    object HomeScreen : Screen(route = HOME_SCREEN)
    object DetailScreen : Screen(route = DETAIL_SCREEN)
}