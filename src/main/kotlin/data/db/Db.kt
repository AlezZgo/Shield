package data.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

interface Db {

    suspend fun persons()

    class Base{
        fun connect(){
            val db = Database.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC")
            TransactionManager.manager.defaultIsolationLevel =
                Connection.TRANSACTION_SERIALIZABLE
        }

    }
}