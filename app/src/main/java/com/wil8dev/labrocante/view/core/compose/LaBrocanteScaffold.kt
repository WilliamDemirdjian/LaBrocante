package com.wil8dev.labrocante.view.core.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wil8dev.labrocante.view.core.compose.icons.rememberChat

@Composable
fun LaBrocanteScaffold(
    modifier: Modifier = Modifier,
    topBarText: String? = null,
    showCart: Boolean = false,
    selectedNavigationBarItem: LaBrocanteNavigationBarItem? = null,
    onHomeClicked: () -> Unit = {},
    onAccountClicked: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val items = listOf(
        LaBrocanteNavigationItemData(
            icon = Icons.Default.Home,
            label = "Home",
            selected = selectedNavigationBarItem is LaBrocanteNavigationBarItem.Home,
            onClick = onHomeClicked,
        ),
        LaBrocanteNavigationItemData(
            icon = Icons.Default.Search,
            label = "Categories",
            selected = selectedNavigationBarItem is LaBrocanteNavigationBarItem.Categories,
            onClick = { /* Handle categories click */ },
        ),
        LaBrocanteNavigationItemData(
            icon = Icons.Default.AddCircle,
            label = "Sell",
            selected = selectedNavigationBarItem is LaBrocanteNavigationBarItem.Sell,
            onClick = { /* Handle sell click */ },
        ),
        LaBrocanteNavigationItemData(
            icon = rememberChat(),
            label = "Chat",
            selected = selectedNavigationBarItem is LaBrocanteNavigationBarItem.Chat,
            onClick = { /* Handle chat click */ },
        ),
        LaBrocanteNavigationItemData(
            icon = Icons.Default.Person,
            label = "Account",
            selected = selectedNavigationBarItem is LaBrocanteNavigationBarItem.Account,
            onClick = onAccountClicked,
        )
    )

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            if (topBarText != null) {
                TopBar(
                    text = topBarText,
                    showCart = showCart,
                )
            }
        },
        bottomBar = {
            if (selectedNavigationBarItem != null) {
                BottomNavigationBar(
                    items = items,
                )
            }
        },
    ) { innerPadding ->
        content(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    showCart: Boolean,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = text,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        },
        actions = {
            if (showCart) {
                IconButton(
                    onClick = { /* TODO: Handle cart click */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color.Black,
                    )
                }
            }
        },
        colors = TopAppBarColors(
            containerColor = Color(0xFFEEEEEE),
            scrolledContainerColor = Color.White,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black,
        ),
    )
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<LaBrocanteNavigationItemData>,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        contentColor = Color.DarkGray,
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 10.sp,
                    )
                },
                selected = item.selected,
                onClick = item.onClick,
            )
        }
    }
}

data class LaBrocanteNavigationItemData(
    val icon: ImageVector,
    val label: String,
    val selected: Boolean,
    val onClick: () -> Unit,
)

sealed class LaBrocanteNavigationBarItem {
    data object Home : LaBrocanteNavigationBarItem()
    data object Categories : LaBrocanteNavigationBarItem()
    data object Sell : LaBrocanteNavigationBarItem()
    data object Chat : LaBrocanteNavigationBarItem()
    data object Account : LaBrocanteNavigationBarItem()
}
