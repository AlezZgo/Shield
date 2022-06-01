package ui.screens.description

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import data.db.models.Common
import ui.navigation.NavController

@Composable
fun DescriptionScreen(
    navController: NavController,
//    common: Common
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
//            common.paramList.forEach{ params->
//                Text(text = params.name, fontWeight = FontWeight.Bold)
//                params.params.forEach{ param->
//                    if(param.){
//                        Text(modifier = Modifier.fillMaxWidth(), onClick = {
//                            createWindow{
//                                param.full()
//                            }
//                        }, text = "")
//                    }
//                    if(param.isImage){
//
//                    }
                }

            }

        }
//        Button(onClick = {
//            navController.navigateBack()
//        }) {
//            Text("Назад")
//        }
//    }
//
//}