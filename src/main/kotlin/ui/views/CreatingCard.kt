package ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.db.tables.CustomTable
import extensions.asFilterParam
import extensions.screens.main.MainViewModel
import org.jetbrains.exposed.sql.FloatColumnType
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.StringColumnType
import org.jetbrains.exposed.sql.Table

@Composable
fun CreatingCard(table: CustomTable, viewModel: MainViewModel) {

    var isFinished  by remember { mutableStateOf(false)}
    var model by remember { mutableStateOf(UIModel.emptyModel(table as Table).params.toMutableList()) }

    Card(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        if (isFinished) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "Объект добавлен в базу данных!", modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Column {
                Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    Button(modifier = Modifier.padding(4.dp).weight(1f), onClick = {
                        viewModel.add(UIModel(model.toList()),table)
                        isFinished = true
                    }) {
                        Text(text = "Создать")
                    }
                }

                Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().padding(4.dp)
                    ) {
                        itemsIndexed(model) { index, pair ->
                            Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {

                                var content by remember { mutableStateOf(pair.second) }

                                Text(
                                    text = pair.first,
                                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                OutlinedTextField(
                                    value = content,
                                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                                    onValueChange = { newValue->

                                        when ((table as Table).columns.drop(1)[index].columnType) {
                                            is IntegerColumnType, is FloatColumnType -> {
                                                if (newValue.all { it.isDigit() }) {
                                                    content = newValue
                                                    model[index] = pair.first to newValue
                                                }
                                            }
                                            is StringColumnType -> {
                                                content = newValue
                                                model[index] = pair.first to newValue
                                            }
                                            else -> throw RuntimeException("Unknown")
                                        }

                                    })
                            }
                        }
                    }
                }


            }
        }
    }
}