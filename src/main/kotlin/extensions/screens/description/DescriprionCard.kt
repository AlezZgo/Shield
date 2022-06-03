package extensions.screens.description

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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
fun DescriptionScreen(model: UIModel,table: CustomTable, viewModel: MainViewModel, ) {

    var isEditMode by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        Column {
            Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                Button(modifier = Modifier.padding(4.dp).weight(1f), onClick = {
                    isEditMode = !isEditMode
                }){
                    Text(text = "Изменить")
                }
                Button(modifier = Modifier.padding(4.dp).weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White),
                    onClick = {
                        viewModel.delete(model,table)
                }){
                    Text(text = "Удалить")
                }
            }
            Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                ) {
                    items(model.params) { pair ->
                        Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                            Text(
                                text = pair.first,
                                modifier = Modifier.fillMaxWidth().padding(4.dp),
                                fontWeight = FontWeight.Bold
                            )
                            OutlinedTextField(
                                value = pair.second,
                                modifier = Modifier.fillMaxWidth().padding(4.dp),
                                enabled = isEditMode,
                                onValueChange = {})
                        }
                    }
                }
            }
        }

    }
}