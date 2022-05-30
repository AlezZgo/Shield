package data.db

import data.db.tables.AddressTable
import data.db.tables.PersonTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

interface Db {

    fun database(): Database

    class Base : Db {

        override fun database(): Database {
            TransactionManager.manager.defaultIsolationLevel =
                Connection.TRANSACTION_SERIALIZABLE


            val db = Database.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC")


            transaction(db) {
                addLogger(StdOutSqlLogger)
                SchemaUtils.create(AddressTable, PersonTable)

                for (i in 0..100) {
                    PersonTable.insert {
                        it[name] = "Александр"
                        it[age] = 22
                        it[weight] = 74f
                        it[address] = 2202
                    }

                }

            }

            return db
        }


    }
}