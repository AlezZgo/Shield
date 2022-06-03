package ui.screens.main

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
import androidx.compose.ui.unit.dp
import extensions.asFilterParam
import org.jetbrains.exposed.sql.FloatColumnType
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.StringColumnType
import ui.views.ObjectPreviewCard
import ui.views.Spinner

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {

    val commons = viewModel.commons.collectAsState()
    val currentTable = viewModel.currentTable.collectAsState()

    Row(
        modifier = Modifier.background(Color.LightGray)
    ) {
        Card(
            modifier = Modifier.weight(1f).fillMaxHeight().padding(4.dp)
        ) {
            Column {
                Spinner(viewModel)
            }
        }

        Column(
            modifier = Modifier.weight(4f).fillMaxHeight()
        ) {
            Column {
                Card(modifier = Modifier.padding(top = 4.dp, end = 4.dp)) {
                    Column {
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(4)
                        ) {
                            items(currentTable.value.columns.drop(1)) { column ->
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    var field by remember { mutableStateOf("") }
                                    TextField(value = field,
                                        singleLine = true,
                                        onValueChange= { newValue ->
                                            val block = {
                                                field = newValue
                                                viewModel.filters.value.removeIf {
                                                    it.column == column
                                                }
                                                if (newValue.isNotEmpty()) {
                                                    viewModel.filters.value.add(column.asFilterParam(newValue))
                                                }
                                            }
                                            when (column.columnType) {
                                                is IntegerColumnType, is FloatColumnType -> {
                                                    if (newValue.all { it.isDigit() }) {
                                                        block.invoke()
                                                    }
                                                }
                                                is StringColumnType -> {
                                                    block.invoke()
                                                }
                                                else -> throw RuntimeException("Unknown")
                                            }
                                        },
                                        label = { Text(column.name) })
                                }
                            }
                        }
                        Button(modifier = Modifier.fillMaxWidth().padding(8.dp), onClick = {
                            viewModel.refresh()
                        }) {
                            Text("Поиск")
                        }

                    }
                }
                Box(
                    modifier = Modifier.weight(1f).padding(top = 4.dp).fillMaxSize()
                ) {
                    val state = rememberLazyListState()

                    LazyColumn(
                        modifier = Modifier, state
                    ) {
                        items(commons.value) { common ->
                            ObjectPreviewCard(common) {

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


