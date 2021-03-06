package extensions.screens.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.db.tables.CustomTable
import extensions.asFilterParam
import extensions.screens.description.DescriptionScreen
import extensions.screens.openWindow
import extensions.toRussian
import org.jetbrains.exposed.sql.FloatColumnType
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.StringColumnType
import ui.views.CreatingCard
import ui.views.ObjectPreviewCard
import ui.views.Spinner

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {

    val commons = viewModel.commons.collectAsState()
    var saved by remember { mutableStateOf(false) }
    val currentTable = viewModel.currentTable.collectAsState()

    if(saved){
        AlertDialog(onDismissRequest = {},
            title = { Text("Экспорт данных в Excel") },
            confirmButton = {
                Button(onClick = {
                    viewModel.exportCurrentDataToExcel()
                    saved = false
                }) {
                    Text("Сохранить")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    saved = false
                }) {
                    Text("Отмена")
                }
            },
            text = { Text("Вы уверены?") })
    }

    Row(
        modifier = Modifier.background(Color.LightGray)
    ) {
        Card(
            modifier = Modifier.width(180.dp).fillMaxHeight().padding(4.dp)
        ) {

            Column {
                Spinner(viewModel)
                Row {
                    Button(modifier = Modifier.weight(1f).padding(4.dp), onClick = {
                        openWindow("Добавить") {
                            CreatingCard(currentTable.value as CustomTable, viewModel)
                        }
                    }) {
                        Image(
                            painterResource("plus.png"),
                            contentDescription = "",
                            modifier = Modifier.size(40.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                    Button(modifier = Modifier.weight(1f).padding(4.dp),
                        onClick = {
                            saved = true
                        }) {
                        Image(
                            painterResource("excel_office.png"),
                            contentDescription = "",
                            modifier = Modifier.size(40.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }

            }
        }

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Column {
                Card(modifier = Modifier.padding(top = 4.dp, end = 4.dp, bottom = 4.dp)) {
                    Column {
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(4)
                        ) {
                            items(currentTable.value.columns.drop(1)) { column ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(4.dp)
                                ) {
                                    var field by remember { mutableStateOf("") }
                                    OutlinedTextField(value = field,
                                        singleLine = true,
                                        onValueChange = { newValue ->
                                            if (newValue.length > 100) return@OutlinedTextField
                                            val block = {
                                                field = newValue
                                                viewModel.filters.value.removeIf {
                                                    it.column == column
                                                }
                                                if (newValue.isNotEmpty()) {
                                                    viewModel.filters.value.add(column.asFilterParam(newValue))
                                                }
                                                viewModel.refresh()
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
                                        label = { Text(column.name.toRussian()) })
                                }
                            }
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
                        items(commons.value) { model ->
                            ObjectPreviewCard(model) {
                                openWindow(model.params.first().second) {
                                    DescriptionScreen(model, currentTable.value as CustomTable, viewModel)
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


