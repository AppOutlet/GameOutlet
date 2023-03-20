package appoutlet.gameoutlet.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File

private const val QUALIFIER_DATABASE_FOLDER = "databaseFolder"

val databaseModule = module {
    factory(named(QUALIFIER_DATABASE_FOLDER)) {
        val userHome = System.getenv("HOME")
        val databaseFolder = "$userHome/.config/appoutlet/gameoutlet/database"
        createDatabaseFolderIfItDoesntExists(databaseFolder)
        "jdbc:sqlite:$databaseFolder/GameOutlet.db"
    }

    single<SqlDriver> {
        val driver = JdbcSqliteDriver(get(named(QUALIFIER_DATABASE_FOLDER)))
        GameOutletDatabase.Schema.create(driver)
        driver
    }

    single { GameOutletDatabase(get()) }
}

private fun createDatabaseFolderIfItDoesntExists(databaseFolder: String) {
    val file = File(databaseFolder)
    if (!file.exists()) {
        file.mkdirs()
    }
}
