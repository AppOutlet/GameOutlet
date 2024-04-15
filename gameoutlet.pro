# internal classes
-keep class appoutlet.gameoutlet.repository.deals.api.** { *; }
-keep class appoutlet.gameoutlet.repository.store.api.** { *; }
-keep class appoutlet.gameoutlet.core.database.*.** { *; }

# dependencies
-keep class app.cash.sqldelight.driver.jdbc.sqlite.*.** { *; }
-keep interface org.osgi.framework.* { *; }
-keep class org.sqlite.** { *; }
-keep class org.javamoney.moneta.spi.** { *; }
-keep class io.ktor.client.engine.java.** { *; }
-keep class com.sun.jna.** { *; }
-keep class com.formdev.flatlaf.ui.** { *; }
-keep class com.formdev.flatlaf.icons.** { *; }

-ignorewarnings
