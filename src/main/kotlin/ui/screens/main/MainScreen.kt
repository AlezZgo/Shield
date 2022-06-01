package ui.screens.main

import Screen
import ui.views.Spinner
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.navigation.NavController
import ui.views.ObjectPreviewCard

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {

    var text by remember { viewModel.requestText }
    val commons = viewModel.commons.collectAsState()

    var expanded by remember { viewModel.expanded }

    Row(
        modifier = Modifier.background(Color.LightGray)
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(4.dp)
        ) {
            Column {
                Spinner(viewModel)
            }
        }

        Column(
            modifier = Modifier
                .weight(4f)
                .fillMaxHeight()
        ) {
            Card {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(modifier = Modifier
                        .weight(4f)
                        .padding(4.dp),
                        singleLine = true,
                        value = text,
                        onValueChange = { newText -> if (newText.length <= 50) text = newText })
                    Button(modifier = Modifier
                        .padding(8.dp),
                        onClick = { viewModel.refresh() }) {
                        Text("Поиск")
                    }
                    Button(
                        modifier = Modifier
                            .padding(8.dp),
                        onClick = {

                        }) {
                        Text("Фильтр")
                    }
                }

            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 4.dp)
                    .fillMaxSize()
            ) {
                val state = rememberLazyListState()

                LazyColumn(
                    modifier = Modifier,
                    state
                ) {
                    items(commons.value) { common ->
                        ObjectPreviewCard(common){
                            navController.navigate(Screen.DescriptionScreen.name)
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                }
                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(
                        scrollState = state
                    )
                )
            }


        }


    }
}