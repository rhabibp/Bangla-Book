package com.timepass.bookreader.navigation

enum class BookReaderScreens {
    DetailsScreen,
    HomeScreen,
    LoginScreen,
    LoginScreen2,
    SearchScreen,
    StatsScreen,
    UpdateScreen,
    SignUpScreen,
    SplashScreen,
    UserRegForm,
    Categories;
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
            LoginScreen2.name -> LoginScreen2
            UserRegForm.name -> UserRegForm
            Categories.name -> Categories

            null -> HomeScreen
            else -> throw IllegalArgumentException("This Route: $route is not found")


        }

    }

}