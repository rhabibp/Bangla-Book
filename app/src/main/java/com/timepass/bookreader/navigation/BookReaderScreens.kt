package com.timepass.bookreader.navigation

enum class BookReaderScreens {
    DetailsScreen,
    HomeScreen,
    LoginScreen,
    SearchScreen,
    StatsScreen,
    UpdateScreen,
    SignUpScreen,
    SplashScreen;
    companion object {
        fun fromRoute(route: String): BookReaderScreens
        = when (route.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            SearchScreen.name -> SearchScreen
            StatsScreen.name -> StatsScreen
            UpdateScreen.name -> UpdateScreen
            DetailsScreen.name -> DetailsScreen
            HomeScreen.name -> HomeScreen
            SignUpScreen.name -> SignUpScreen

            null -> HomeScreen
            else -> throw IllegalArgumentException("This Route: $route is not found")


        }

    }

}