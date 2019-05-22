package nkanyiso.hlela.com.mycurreny.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nkanyiso.hlela.com.mycurreny.data.db.dao.CurrencyDao
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "currency_db"

@Database(
    entities = [
        CurrencyEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun provideCurrency(): CurrencyDao

    companion object {

        @Volatile
        private var instance: CurrencyDatabase? = null

        fun getInstance(context: Context): CurrencyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CurrencyDatabase {
            return Room.databaseBuilder(context, CurrencyDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }
}