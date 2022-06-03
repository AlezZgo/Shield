package extensions.screens.description

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.db.models.UIModel

@Composable
fun DescriptionScreen(model: UIModel) {
    Card(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            ) {
                items(model.params) { pair ->
                    Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                        Text(text = pair.first, modifier = Modifier.fillMaxWidth().padding(4.dp),fontWeight = FontWeight.Bold)
                        OutlinedTextField(value = pair.second,modifier = Modifier.fillMaxWidth().padding(4.dp), enabled = false, onValueChange = {})
                    }
                }
            }
        }
    }
}