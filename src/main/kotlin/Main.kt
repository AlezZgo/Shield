import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.db.tables.AddressesTable
import data.db.tables.PersonsTable
import data.db.tables.RelativesTable
import extensions.screens.main.MainScreen
import extensions.screens.main.MainViewModel
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

@Composable
@Preview
fun App() {
    val tables = listOf<Table>(RelativesTable, AddressesTable, PersonsTable)

    initDB(tables)

    val viewModel = remember { MainViewModel(tables) }

    MaterialTheme {
        MainScreen(viewModel)
    }

}

private fun initDB(tables: List<Table>) {
    val db = Database.connect("jdbc:h2:./db/db", "org.h2.Driver")

    TransactionManager.manager.defaultIsolationLevel =
        Connection.TRANSACTION_SERIALIZABLE

    TransactionManager.defaultDatabase = db



    transaction {
        tables.forEach {
            SchemaUtils.create(it)
        }

    }

    transaction(db) {
//        AddressesTable.deleteWhere {
//            column
//        }
//        AddressesTable.insert {
//            it[address] = "Бери стрит"
//        }
//        AddressesTable.insert {
//            it[address] = "Роялти Вей"
//        }
//        AddressesTable.insert {
//            it[address] = "Плаза лейн"
//        }
//        AddressesTable.insert {
//            it[address] = "Хоп вэй"
//        }
//        AddressesTable.insert {
//            it[address] = "Бэкэр ров"
//        }
//        AddressesTable.insert {
//            it[address] = "Квин лейн"
//        }
//        AddressesTable.insert {
//            it[address] = "Виндмил роут"
//        }
//        AddressesTable.insert {
//            it[address] = "Монумент стрит"
//        }
//        AddressesTable.insert {
//            it[address] = "Голд Лэйн"
//        }
//        AddressesTable.insert {
//            it[address] = "Джейд Пассадж"
//        }
//        AddressesTable.insert {
//            it[address] = "Эмералд авеню"
//        }

//        PersonsTable.insert {
//            it[name] = "Александр  Иванович "
//            it[age] = 36
//            it[weight] = 77f
//            it[address] = 27
//        }
//        PersonsTable.insert {
//            it[name] = "Максим  Витальевич "
//            it[age] = 21
//            it[weight] = 79f
//            it[address] = 29
//        }
//        PersonsTable.insert {
//            it[name] = "Александр  Иванович "
//            it[age] = 36
//            it[weight] = 77f
//            it[address] = 27
//        }
////
//        RelativesTable.insert {
//            it[name] = "Иван Косыгин"
//            it[relationDegree] = "Сын "
//            it[employment] = "Магазин "
//            it[birthDay] = "21.01.2010 "
//            it[birthPlace] = "Москва "
//            it[birthCountry] = "Россия "
//            it[nationality] = "Русская "
//            it[citizen] = "РФ "
//            it[admissionForm] = 2
//        }
//
//
//        RelativesTable.insert {
//            it[name] = "Оганнес Асатрян"
//            it[relationDegree] = "Отец"
//            it[employment] = "Подрядчик"
//            it[birthDay] = "25.01.1992"
//            it[birthPlace] = "Ереван"
//            it[birthCountry] = "Армения"
//            it[nationality] = "Армянин"
//            it[citizen] = "РФ,Армения"
//            it[admissionForm] = 0
//        }

//        }
    }
}

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        icon = painterResource("shield.png"),
        title = "Щит"
    ) {
        App()
    }
}

