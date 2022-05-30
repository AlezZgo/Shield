package di

import data.db.Db
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

val mainModule = module{

    single<Database>{
        Db.Base().database()
    }

}