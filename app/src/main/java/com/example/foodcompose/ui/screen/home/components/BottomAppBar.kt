package com.example.foodcompose.ui.screen.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.foodcompose.BottomBarDestinations
import com.example.foodcompose.Home
import com.example.foodcompose.bottomAppBarScreens
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.SecondaryDark

@Composable
fun BottomAppBar(onTabSelected: (BottomBarDestinations) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .selectableGroup()
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            bottomAppBarScreens.forEach { screen ->
                BottomTab(
                    text = screen.route,
                    icon = screen.icon,
                    onTabSelected = { onTabSelected(screen) })
            }
        }
    }
}

@Composable
fun BottomTab(text: String, icon: Int, onTabSelected: () -> Unit) {
    val selected = text == Home.route
    val tabTintColor = if (selected) Primary else SecondaryDark
    Row(
        modifier = Modifier.selectable(
            selected = selected,
            onClick = onTabSelected,
            role = Role.Tab
        ).padding(bottom = 15.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(35.dp),
            tint = tabTintColor
        )
    }
}
