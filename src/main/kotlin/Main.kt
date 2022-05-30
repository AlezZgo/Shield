// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.zIndex

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("") }
    val names = mutableListOf<String>("Александр", "Дмитрий", "Алексей", "Максим", "Олег")

    MaterialTheme {
        Row(
            modifier = Modifier.background(Color.LightGray)
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(4.dp)
            ) {
                Spinner()

            }

            Column(
                modifier = Modifier
                    .weight(4f)
                    .fillMaxHeight()
            ) {
                Card(
                    modifier = Modifier
                        .padding(0.dp,0.dp,0.dp,4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            modifier = Modifier.weight(4f)
                                .background(Color.White),
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },

                        )
                        Button(
                            modifier = Modifier
                                .padding(8.dp),
                            onClick = {}) {
                            Text("Поиск")
                        }
                        Button(
                            modifier = Modifier
                                .padding(8.dp),
                            onClick = {}) {
                            Text("Фильтр")
                        }
                    }

                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.LightGray)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        items(names) { name ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            ) {
                                Text(

                                    text = name,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }

                        }
                    }
                }


            }


        }

    }

}


fun main() = application {
    Window(onCloseRequest = ::exitApplication,
    title = "Щит") {
        App()
    }
}
