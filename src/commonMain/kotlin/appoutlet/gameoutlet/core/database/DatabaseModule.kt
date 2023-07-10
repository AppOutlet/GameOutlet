package appoutlet.gameoutlet.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import appoutlet.gameoutlet.OS
import appoutlet.gameoutlet.getOS
import ca.gosyer.appdirs.AppDirs
import io.github.aakira.napier.Napier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File

private const val QUALIFIER_DATABASE_FOLDER = "databaseFolder"

val databaseModule = module {
    factory(named(QUALIFIER_DATABASE_FOLDER)) {
        val databaseFolder = getDatabaseFolder()
        Napier.i("Database folder $databaseFolder")
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

private fun getDatabaseFolder(): String {
    return when (getOS()) {
        OS.MAC,
        OS.WINDOWS -> {
            val appDirs = AppDirs(appName = "GameOutlet", appAuthor = "AppOutlet")
            appDirs.getUserDataDir()
        }
        OS.LINUX -> {
            val home = System.getenv("HOME")
            return "$home/.config/AppOutlet/GameOutlet/database"
        }
    }
}

private fun createDatabaseFolderIfItDoesntExists(databaseFolder: String) {
    val file = File(databaseFolder)
    if (!file.exists()) {
        file.mkdirs()
    }
}
