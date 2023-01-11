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

object Home : Path {
    override val route = "home"
}

