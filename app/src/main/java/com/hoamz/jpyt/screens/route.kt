package com.hoamz.jpyt.screens


const val SCREEN_B = "B"
const val SCREEN_A = "A"
const val SCREEN_C = "C"

sealed class Route(val route : String){
    object ScreenA : Route(route = "screen_a")
    object ScreenC : Route(route = "screen_c")
    object ScreenB : Route(route = "screen_b?$SCREEN_B={$SCREEN_B}"){
        fun <T>passDataToScreenB(value : T) : String{
            return "screen_b?$SCREEN_B=$value"
        }
    }

    object LoginScreen : Route(route = "LoginScreen")
    object HomeScreen : Route(route = "HomeScreen")
}


/*
const val HOME = "_home"
const val DETAIL = "_detail"

sealed class Screen(val route: String){
    object Home : Screen("Home_Screen?$HOME={$HOME}"){//route nay o noi nhan
        //value o day phai khop
        fun passValueToHome(value : String) = "Home_Screen?$HOME=$value"//khi muon gui den home thi goi ham nay
    }
    object Detail : Screen("Detail_Screen?$DETAIL={$DETAIL}"){//viet o noi nhan
        fun passValue(value: String) = "Detail_Screen?$DETAIL=$value"//nay la ham gui den detail
    }
    object Pay : Screen("Pay_Screen"){
    }
}

 */