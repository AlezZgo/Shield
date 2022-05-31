package ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ObjectPreviewCard(
    title: String,
    content: String,
    listOfStringPairs: List<Pair<String, String>>,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick

    ) {
        Column {
            listOfStringPairs.take(2).forEach {
                Row(modifier = Modifier.padding(12.dp) ) {
                    Text(text = it.first, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = it.second)
                }
            }

        }

    }
}