package ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ObjectPreviewCard(
    model: UIModel,
    onClick: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth(),onClick = onClick) {
        Text(modifier = Modifier.padding(16.dp), text = model.params.first().second)
    }
}