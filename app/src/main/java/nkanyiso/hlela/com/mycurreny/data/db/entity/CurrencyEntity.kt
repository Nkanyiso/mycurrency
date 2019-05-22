package nkanyiso.hlela.com.mycurreny.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val CURRENCY_TABLE_NAME = "currency"

@Entity(tableName = CURRENCY_TABLE_NAME,indices = [Index("name", unique = true)])
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val description: String,
    val monitored: Boolean =false
)