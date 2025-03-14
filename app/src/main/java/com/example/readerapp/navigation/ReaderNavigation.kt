package com.example.readerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readerapp.screens.SplashScreen
import com.example.readerapp.screens.details.DetailsScreen
import com.example.readerapp.screens.home.HomeScreen
import com.example.readerapp.screens.login.LoginScreen
import com.example.readerapp.screens.search.SearchScreen
import com.example.readerapp.screens.signup.SignUpScreen
import com.example.readerapp.screens.stats.StatsScreen
import com.example.readerapp.screens.update.UpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreenRoute
    ) {
        composable<SplashScreenRoute> {
            SplashScreen(
                navController = navController
            )
        }
        composable<HomeScreenRoute> {
            HomeScreen()
        }
        composable<DetailScreenRoute> {
            DetailsScreen()
        }
        composable<LoginScreenRoute> {
            LoginScreen(
                navController = navController
            )
        }
        composable<SignUpScreenRoute> {
            SignUpScreen(
                navController = navController
            )
        }

        composable<SearchScreenRoute> {
            SearchScreen()
        }
        composable<StatsScreenRoute> {
            StatsScreen()
        }
        composable<UpdateScreenRoute> {
            UpdateScreen()
        }

    }
}