package com.example.testproject.bottomnavigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testproject.R

sealed class BottomNavItem(var title:String = "",var icon:ImageVector = Icons.Default.WaterDrop,var route:String){

    data object Home: BottomNavItem(title = "Home", icon = Icons.Default.WaterDrop, route = "home_screen")
    data object Search: BottomNavItem(title = "Search", icon = Icons.Default.Search, route = "search_screen")
    data object Setting: BottomNavItem(title = "Setting", icon = Icons.Default.Settings, route = "setting_screen")
    data object Another: BottomNavItem(route = "another_screen")
}


@Composable
fun NavGraphBottom(navController: NavHostController){
    
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route){

        composable(route = BottomNavItem.Home.route){
            HomeScreen()
        }

        composable(route = BottomNavItem.Search.route){
            SearchScreen()
        }

        composable(route = BottomNavItem.Setting.route){
            SettingScreen(navController = navController)
        }

        composable(route = BottomNavItem.Another.route){
            AnotherScreen()
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController){

    val items = listOf(BottomNavItem.Home,BottomNavItem.Search,BottomNavItem.Setting)


    NavigationBar(containerColor = Color(0xFF232A3D), modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .clip(
            RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)
        )) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            items.forEach { item ->
                AnimatedBottomNavigationItem(selected = currentRoute == item.route,

                    onClick = {
                        navController.navigate(item.route){
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }

                    },

                    icon = item.icon, label = "Home"
                )

            }
        }


    }
}


@SuppressLint( "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavScreenView(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(modifier = Modifier.height(32.dp),
        bottomBar = { if (navBackStackEntry?.destination?.route != BottomNavItem.Another.route){
            BottomBar(navController = navController)
        }
     }
    ) {

        NavGraphBottom(navController = navController)
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AnimatedBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    enabled: Boolean = true,
    label: String,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = 0.5f),
) {

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .bounceClick {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, tint = if (selected) Color.White else Color.Gray , modifier = Modifier
            .height(24.dp)
            .width(26.dp), contentDescription = "")


    }
}


fun Modifier.bounceClick(
    scaleDown: Float = 0.6f,
    onClick: () -> Unit
) = composed {

    val interactionSource = remember { MutableInteractionSource() }

    val animatable = remember {
        androidx.compose.animation.core.Animatable(1f)
    }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> animatable.animateTo(scaleDown)
                is PressInteraction.Release -> animatable.animateTo(1f)
                is PressInteraction.Cancel -> animatable.animateTo(1f)
            }
        }
    }

    Modifier
        .graphicsLayer {
            val scale = animatable.value
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        }
}