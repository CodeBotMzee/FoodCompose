package com.example.foodcompose


interface Path {
    val route: String
}

object Splash : Path {
    override val route = "splash"
}

object SignUpLogin : Path {
    override val route = "signup_login"
}


interface BottomBarDestinations {
    val icon: Int
    val route: String
}

object Home : BottomBarDestinations {
    override val route = "home"
    override val icon = R.drawable.home
}

object Favorite : BottomBarDestinations {
    override val route = "favourite"
    override val icon = R.drawable.heart
}

object Profile : BottomBarDestinations {
    override val route = "profile"
    override val icon = R.drawable.user
}

object OrderHistory : BottomBarDestinations {
    override val route = "order_history"
    override val icon = R.drawable.order_history
}

val bottomAppBarScreens = listOf(Home,Favorite,Profile,OrderHistory)


