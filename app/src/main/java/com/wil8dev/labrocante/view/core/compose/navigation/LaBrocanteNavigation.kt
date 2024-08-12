package com.wil8dev.labrocante.view.core.compose.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wil8dev.labrocante.view.MainActivity
import com.wil8dev.labrocante.view.account.AccountView
import com.wil8dev.labrocante.view.assistant.AssistantView
import com.wil8dev.labrocante.view.home.HomeView
import com.wil8dev.labrocante.view.prompt.PromptView
import com.wil8dev.labrocante.view.welcome.WelcomeView

@Composable
fun MainActivity.LaBrocanteNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Home.route,
    ) {
        laBrocanteNavigation(
            navController = navController,
            route = Destination.Home.route,
            onBack = { finish() },
        ) {
            HomeView(
                navigateToWelcome = { navController.navigate(route = Destination.Welcome.route) },
                navigateToAssistant = { navController.navigate(route = Destination.Assistant.route) },
                onAccountClicked = { navController.navigate(route = Destination.Account.route) },
            )
        }
        laBrocanteNavigation(
            navController = navController,
            route = Destination.Welcome.route,
            slideFromBottom = true,
            onBack = { navController.navigate(route = Destination.Home.route) },
        ) {
            WelcomeView(
                navigateToHome = { navController.navigate(route = Destination.Home.route) },
            )
        }
        laBrocanteNavigation(
            navController = navController,
            route = Destination.Assistant.route,
            onBack = { navController.navigate(route = Destination.Home.route) },
        ) {
            AssistantView(
                navigateToPrompt = { isSelling ->
                    navController.navigate(
                        route = "${Destination.Prompt.route}/$isSelling"
                    )
                },
            )
        }
        laBrocanteNavigation(
            navController = navController,
            route = "${Destination.Prompt.route}/{isSelling}",
            onBack = { navController.navigate(route = Destination.Assistant.route) },
        ) { navHostController ->
            val isSellingString = navHostController.currentBackStackEntry?.arguments?.getString("isSelling")
            val isSelling = isSellingString?.toBoolean() ?: false
            PromptView(
                isSelling = isSelling,
            )
        }
        laBrocanteNavigation(
            navController = navController,
            route = Destination.Account.route,
            onBack = { navController.navigate(route = Destination.Home.route) },
        ) {
            AccountView(
                onHomeClicked = { navController.navigate(route = Destination.Home.route) },
            )
        }
    }
}

fun NavGraphBuilder.laBrocanteNavigation(
    navController: NavHostController,
    route: String,
    slideFromBottom: Boolean = false,
    onBack: () -> Unit = {},
    content: @Composable (NavHostController) -> Unit,
) {
    val durationMillis = 300
    composable(
        route = route,
        enterTransition = {
            slideIntoContainer(
                towards = if (slideFromBottom) AnimatedContentTransitionScope.SlideDirection.Up else AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(durationMillis),
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = if (slideFromBottom) AnimatedContentTransitionScope.SlideDirection.Down else AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(durationMillis),
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = if (slideFromBottom) AnimatedContentTransitionScope.SlideDirection.Up else AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis),
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = if (slideFromBottom) AnimatedContentTransitionScope.SlideDirection.Down else AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis),
            )
        },
    ) {
        OnBackPressedHandler(onBack)
        content(navController)
    }
}

@Composable
private fun OnBackPressedHandler(
    onBack: () -> Unit,
) {
    BackHandler(
        enabled = true,
    ) {
        onBack()
    }
}
