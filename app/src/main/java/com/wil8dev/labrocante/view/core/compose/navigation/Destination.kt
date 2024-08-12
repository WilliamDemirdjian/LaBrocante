package com.wil8dev.labrocante.view.core.compose.navigation

sealed class Destination(
    val route: String,
) {

    data object Home : Destination(route = "home")
    data object Welcome : Destination(route = "welcome")
    data object Assistant : Destination(route = "assistant")
    data object Prompt : Destination(route = "prompt")
    data object Account : Destination(route = "account")
}
