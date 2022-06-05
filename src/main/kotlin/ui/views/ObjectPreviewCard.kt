package ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.db.tables.CustomTable
import extensions.screens.openWindow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ObjectPreviewCard(
    model: UIModel,
    onClick: () -> Unit,
    onDeleteClick: ()-> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row {
            Card(onClick = onClick, modifier = Modifier.weight(1f)){
                Text(text = model.params.first().second,modifier = Modifier.padding(16.dp))
            }

            OutlinedButton(modifier = Modifier.padding(4.dp), onClick = onDeleteClick) {
                Image(
                    painterResource("trashCan.png"),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }
        }

    }
}