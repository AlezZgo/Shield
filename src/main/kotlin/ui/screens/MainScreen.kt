package ui.screens

import Spinner
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
import data.db.models.Person
import kotlinx.coroutines.launch
import ui.navigation.NavController

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {

    var text by remember { viewModel.requestText }
    val persons = viewModel.persons.collectAsState()

    val coroutineScope = rememberCoroutineScope()

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
                Spinner()

                //                Button(onClick = {
//                    navController.navigate(Screen.DescriptionScreen.name)
//                }){
//                    Text("go to description")
//                }
            }


        }

        Column(
            modifier = Modifier
                .weight(4f)
                .fillMaxHeight()
        ) {
            Card(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
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
                        onClick = {
                            coroutineScope.launch { viewModel.refresh() }

                        }) {
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
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    items(persons.value) { person ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Text(

                                text = person.name,
                                modifier = Modifier.padding(4.dp)
                            )
                        }

                    }
                }
            }


        }


    }
}