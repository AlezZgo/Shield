import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.db.tables.Addresses
import data.db.tables.Persons
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionScope
import ui.navigation.NavController
import ui.navigation.NavigationHost
import ui.navigation.composable
import ui.navigation.rememberNavController
import ui.screens.DescriptionScreen
import ui.screens.MainViewModel
import java.sql.Connection

@Composable
@Preview
fun App() {

    val db = Database.connect("jdbc:sqlite:file:test?mode=memory&cache=shared", "org.sqlite.JDBC")

    TransactionManager.manager.defaultIsolationLevel =
        Connection.TRANSACTION_SERIALIZABLE

    transaction{
        SchemaUtils.create(Addresses, Persons)

        Persons.insert {
            it[name] = "Михаил"
            it[age] = 36
            it[weight] = 100f
            it[address] = 1212
        }
    }

    val navController by rememberNavController(Screen.MainScreen.name)

    MaterialTheme {

        MainScreen(navController,MainViewModel(db))
        CustomNavigationHost(navController,MainViewModel(db))
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
