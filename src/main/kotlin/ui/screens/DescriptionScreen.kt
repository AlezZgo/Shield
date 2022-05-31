package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.navigation.NavController

@Composable
fun DescriptionScreen(
    navController: NavController
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Hello world!")
            Button(onClick = {
                navController.navigateBack()
            }) {
                Text("navigate")
            }
        }
    }

}