// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import navigation.NavController
import navigation.NavigationHost
import navigation.composable
import navigation.rememberNavController
import screens.DescriptionScreen

@Composable
@Preview
fun App() {

    val navController by rememberNavController(Screen.MainScreen.name)

    MaterialTheme {

        MainScreen(navController)
        CustomNavigationHost(navController)
    }

}


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Щит"
    ) {
        App()
    }
}

enum class Screen{
    MainScreen,
    DescriptionScreen,
}


@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    NavigationHost(navController) {
        composable(Screen.MainScreen.name) {
            MainScreen(navController)
        }

        composable(Screen.DescriptionScreen.name) {
            DescriptionScreen(navController)
        }
    }.build()
}
