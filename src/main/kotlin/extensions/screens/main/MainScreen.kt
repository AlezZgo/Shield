package extensions.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import extensions.asFilterParam
import extensions.screens.description.DescriptionScreen
import extensions.screens.description.openDescriptionWindow
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
            modifier = Modifier.width(200.dp).fillMaxHeight().padding(4.dp)
        ) {
            Column {
                Spinner(viewModel)
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Column {
                Card(modifier = Modifier.padding(top = 4.dp, end = 4.dp)) {
                    Column {
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(4)
                        ) {
                            items(currentTable.value.columns.drop(1)) { column ->
                                Column(horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(4.dp)) {
                                    var field by remember { mutableStateOf("") }
                                    OutlinedTextField(value = field,
                                        singleLine = true,
                                        onValueChange = { newValue ->
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
                                openDescriptionWindow(common.params.first().first) {
                                    DescriptionScreen(common)
                                }
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


