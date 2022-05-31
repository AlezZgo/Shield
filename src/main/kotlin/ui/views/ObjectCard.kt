package ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ObjectCard(title: String, content: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)

    ) {
        Column {
            Text(
                text = title,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = content,
                modifier = Modifier.padding(4.dp)
            )
        }

    }
}