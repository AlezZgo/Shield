import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Spinner() {

    val tablesList = mutableListOf<String>("Персонал", "Табель", "Места", "Знакомые", "Люди")

    var currentTable by remember { mutableStateOf(tablesList[0]) }
    var expanded by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Row(
            Modifier
                .padding(18.dp)
                .clickable {
                    expanded = !expanded
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) { // Anchor view
            Text(text = currentTable, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp)) // Country name label
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")

            //
            DropdownMenu(expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
                tablesList.forEach { country ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        currentTable = country
                    }) {
                        Text(text = country)
                    }
                }
            }
        }
    }

}