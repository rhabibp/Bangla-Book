package com.timepass.bookreader.navigation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.timepass.bookreader.screens.BookReaderSplashScreen
import com.timepass.bookreader.screens.SignUp.SignUpScreen
import com.timepass.bookreader.screens.UserRegForm
import com.timepass.bookreader.screens.categories.BookCategories
import com.timepass.bookreader.screens.detail.DetailsScreen
import com.timepass.bookreader.screens.detail.DetailsViewModel
import com.timepass.bookreader.screens.home.HomeScreen
import com.timepass.bookreader.screens.login.LoginScreen2
import com.timepass.bookreader.screens.search.SearchScreen
import com.timepass.bookreader.screens.stats.StatsScreen
import com.timepass.bookreader.screens.update.UpdateScreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BookReaderScreens.SplashScreen.name)
    {
        composable(BookReaderScreens.SplashScreen.name){
            BookReaderSplashScreen(navController = navController)
        }
        composable(BookReaderScreens.HomeScreen.name){
            HomeScreen(navController = navController,detailsViewModel = DetailsViewModel())
        }
//        composable(BookReaderScreens.LoginScreen.name){
//            LoginScreen(navController = navController)
//        }
        composable(BookReaderScreens.LoginScreen2.name){
            LoginScreen2(navController = navController)
        }
        composable(BookReaderScreens.UserRegForm.name){
            UserRegForm(navController = navController)
        }

        val detailTitle = BookReaderScreens.DetailsScreen.name
        composable("$detailTitle/{title}/{author}/{bookDescription}/{bookDownloadLink}/{bookImageLink}", arguments = listOf(navArgument("title"){
            type = NavType.StringType
        },
            navArgument("author"){
                type = NavType.StringType
            },
            navArgument("bookDescription"){
                type = NavType.StringType
            },
            navArgument("bookDownloadLink"){
                type = NavType.StringType
            },
            navArgument("bookImageLink"){
                type = NavType.StringType
            }
        )){ backStackEntry ->
              val title=  backStackEntry.arguments?.getString("title")
              val author=  backStackEntry.arguments?.getString("author")
            val bookDescription = backStackEntry.arguments?.getString("bookDescription")
            val bookDownloadLink = backStackEntry.arguments?.getString("bookDownloadLink")
            val bookImageLink = backStackEntry.arguments?.getString("bookImageLink")
                    DetailsScreen(
                        navController = navController, title = title.toString(), author =author.toString(),
                        bookDescription = bookDescription.toString(),
                        bookDownloadLink = Uri.parse(bookDownloadLink),
                        bookImageLink = Uri.parse(bookImageLink)

                    )
        }
        composable(BookReaderScreens.UpdateScreen.name){
            UpdateScreen(navController = navController)
        }
        composable(BookReaderScreens.StatsScreen.name){
            StatsScreen(navController = navController)
        }
        composable(BookReaderScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }
        composable(BookReaderScreens.SignUpScreen.name){
            SignUpScreen(navController = navController)
        }

        composable(BookReaderScreens.Categories.name){
            BookCategories(navController = navController)
        }
    }
}