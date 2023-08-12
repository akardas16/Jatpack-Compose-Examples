package com.example.testproject.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


//Argument Keys
const val USERNAME_ARG = "name"
const val USERAGE_ARG = "age"

//AllScreens
sealed class Screen(val route:String) {

    data object Home: Screen(route = "home_screen")
    data object Second: Screen(route = "second_screen")
    data object ThirdScreen: Screen(route = "third_screen/{$USERNAME_ARG}/{$USERAGE_ARG}"){

        fun passUserData(userName:String,age:Int):String {
            return "third_screen/$userName/$age"
        }
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = Screen.Home.route
    ) {

        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(route = Screen.Second.route) {
            SecondScreen(navController)
        }
        
        composable(route = Screen.ThirdScreen.route,
            arguments = listOf(
                navArgument(USERNAME_ARG){
                type = NavType.StringType
            },
                navArgument(USERAGE_ARG){
                    type = NavType.IntType
                }
            )){

            Log.i("sdfereterer",
                "SetupNavGraph: ${it.arguments?.getString(USERNAME_ARG)} --- ${it.arguments?.getInt(
                    USERAGE_ARG
                )}")
            ThirdScreen(navController = navController, username = it.arguments?.getString(USERNAME_ARG))
        }
    }
}


/*
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {

    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        )
            .apply { targetState = true },
        modifier = Modifier,
        enter =   slideInHorizontally(initialOffsetX = { it / 2 }),
        exit = slideOutHorizontally(targetOffsetX = {it / 2})
    ) {
        content()
    }
}

@Composable
fun ExitAnimation(content: @Composable () -> Unit) {

    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        )
            .apply { targetState = true },
        modifier = Modifier,
    ) {
        content()
    }
}*/
