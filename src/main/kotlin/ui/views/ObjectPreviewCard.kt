package ui.views

import androidx.compose.foundation.layout.Column
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
fun ObjectPreviewCard(title: String, content: String,onClick: ()->Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick

    ) {
        Column(modifier = Modifier.padding(4.dp),) {
            Text(text = title)
            Text(text = content)
        }

    }
}