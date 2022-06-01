import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.db.tables.AddressesTable
import data.db.tables.PersonsTable
import data.db.tables.RelativesTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import ui.navigation.NavController
import ui.navigation.NavigationHost
import ui.navigation.composable
import ui.navigation.rememberNavController
import ui.screens.description.DescriptionScreen
import ui.screens.main.MainScreen
import ui.screens.main.MainViewModel
import java.sql.Connection

@Composable
@Preview
fun App() {

    val db = Database.connect("jdbc:sqlite:db/data.db", "org.sqlite.JDBC")

    TransactionManager.manager.defaultIsolationLevel =
        Connection.TRANSACTION_SERIALIZABLE

    TransactionManager.defaultDatabase = db

    val tables = listOf<Table>(AddressesTable, PersonsTable,RelativesTable)

    transaction{
        tables.forEach {
            SchemaUtils.create(it)
        }

    }

    transaction(db) {
//        for (i in 0..100){
//            AddressesTable.insert {
//                it[address] = "$i Ватутина> "
//            }
//        }
//        for (i in 0..100){
//            PersonsTable.insert {
//                it[name] = "$i Михаил Задорнович "
//                it[age] = 2
//                it[weight] = 3f
//                it[address] = 2
//            }
//        }
//        for (i in 0..100){
//            RelativesTable.insert {
//                it[name] = "$i Буба "
//                it[relationDegree] = "$i Сын "
//                it[employment] = "$i Школьник "
//                it[birthDay] = "$i 23.01.2010 "
//                it[birthPlace] = "$i Москва "
//                it[birthCountry] = "$i Россия "
//                it[nationality] = "$i Русский "
//                it[citizen] = "$i РФ "
//                it[admissionForm] = 2
//            }
//        }
    }

    val navController by rememberNavController(Screen.MainScreen.name)

    val viewModel = remember{ MainViewModel(tables) }

    MaterialTheme {
        MainScreen(navController,viewModel)
        CustomNavigationHost(navController,viewModel)
    }

}


fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "Щит"
    ) {
        App()
    }
}


enum class Screen {
    MainScreen,
    DescriptionScreen,
}

@Composable
fun CustomNavigationHost(
    navController: NavController,
    viewModel: MainViewModel
) {
    NavigationHost(navController) {
        composable(Screen.MainScreen.name) {
            MainScreen(navController, viewModel)
        }

        composable(Screen.DescriptionScreen.name) {
            DescriptionScreen(navController)
        }
    }.build()
}
