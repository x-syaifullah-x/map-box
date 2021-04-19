package id.xxx.map.box.search.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import id.xxx.map.box.search.data.source.local.database.AppDatabase
import id.xxx.map.box.search.data.source.remote.service.ApiConfig

object Helper {
    val context = InstrumentationRegistry.getInstrumentation().targetContext


    val databaseInMemory = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

    val database = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()

    fun <T> apiService(t: Class<T>): T = ApiConfig.service(BuildConfig.BASE_URL).create(t)
}