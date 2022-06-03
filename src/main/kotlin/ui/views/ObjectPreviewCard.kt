package ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.db.models.UIModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ObjectPreviewCard(
    model: UIModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick

    ) {
        Column {
            Row(modifier = Modifier.padding(12.dp)) {
                Text(text = model.params.first().first, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = model.params.first().second)
            }
        }

    }
}