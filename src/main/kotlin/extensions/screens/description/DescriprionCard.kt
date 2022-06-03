package extensions.screens.description

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.db.tables.CustomTable
import extensions.screens.main.MainViewModel
import ui.views.UIModel


@Composable
fun DescriptionScreen(model: UIModel, table: CustomTable, viewModel: MainViewModel) {

    var isEditMode by remember { mutableStateOf(false) }
    var newModel by remember {mutableStateOf(model.params.toMutableList())}

    Card(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        Column {
            if(isEditMode){
                Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    Button(modifier = Modifier.padding(4.dp).weight(1f), onClick = {
                        viewModel.edit(model,UIModel(newModel.toList()),table)
                        isEditMode = false
                    }) {
                        Text(text = "Сохранить")
                    }
                    OutlinedButton(modifier = Modifier.padding(4.dp).weight(1f),
                        border= BorderStroke(1.dp, Color.Blue),
                        onClick = {
                            isEditMode = false
                        }) {
                        Text(text = "Отмена")
                    }
                }
            }else{
                Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    Button(modifier = Modifier.padding(4.dp).weight(1f), onClick = {
                        isEditMode = !isEditMode
                    }) {
                        Text(text = "Изменить")
                    }
                    Button(modifier = Modifier.padding(4.dp).weight(1f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White),
                        onClick = {
                            viewModel.delete(UIModel(newModel.toList()), table)
                        }) {
                        Text(text = "Удалить")
                    }
                }
            }
            Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                ) {
                    itemsIndexed(model.params) { index, pair ->
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
                                enabled = isEditMode,
                                onValueChange = {
                                    content = it
                                    newModel[index] = pair.first to it
                                })
                        }
                    }
                }
            }


        }
    }
}