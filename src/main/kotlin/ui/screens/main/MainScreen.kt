package ui.screens.main

import Screen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import data.db.models.params.FloatFilterParam
import data.db.models.params.IntFilterParam
import data.db.models.params.StringFilterParam
import extensions.asFilterParam
import extensions.keyBoardTypeType
import kotlinx.coroutines.flow.asStateFlow
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.FloatColumnType
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.StringColumnType
import ui.navigation.NavController
import ui.views.ObjectPreviewCard
import ui.views.Spinner
import java.lang.RuntimeException

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {

    var filters = viewModel.filters.asStateFlow()
    val commons = viewModel.commons.collectAsState()
    val currentTable = viewModel.currentTable.collectAsState()

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
            Column{
                Card(modifier = Modifier.padding(top = 4.dp, end = 4.dp)) {
                    Column {
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(4)
                        ) {
                            items(currentTable.value.columns.drop(1)) { column ->
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    var field by remember { mutableStateOf("") }
                                    TextField(value = field,
                                        //todo: не работает тк через field меняется...
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        onValueChange = { newValue->
                                            field = newValue
                                            viewModel.filters.value.add(column.asFilterParam(newValue))
                                        },
                                        label = { Text(column.name) })
                                }
                            }
                        }

/*
//                    OutlinedTextField(modifier = Modifier
//                        .padding(4.dp),
//                        singleLine = true,
//                        value = text,
//                        onValueChange = { newText -> if (newText.length <= 50) text = newText })

//                    CustomTextField(
//                        leadingIcon = {
//                            Icon(
//                                Icons.Filled.Search,
//                                null,
//                                tint = LocalContentColor.current.copy(alpha = 0.3f)
//                            )
//                        },
//                        trailingIcon = null,
//                        modifier = Modifier
//                            .background(
//                                MaterialTheme.colors.surface,
//                                RoundedCornerShape(percent = 50)
//                            )
//                            .padding(4.dp)
//                            .height(40.dp),
//                        fontSize = 10.sp,
//                        placeholderText = "Поиск"
//                    )
*/

                        Button(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            onClick = { viewModel.refresh() }) {
                            Text("Поиск")
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
                            ObjectPreviewCard(common) {
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


}
