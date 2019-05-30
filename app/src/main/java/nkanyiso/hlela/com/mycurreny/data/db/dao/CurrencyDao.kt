package nkanyiso.hlela.com.mycurreny.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import nkanyiso.hlela.com.mycurreny.data.db.entity.CURRENCY_TABLE_NAME
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg currency: CurrencyEntity): List<Long>

    @Query("SELECT * FROM $CURRENCY_TABLE_NAME WHERE monitored = :B")
    fun select(B: Boolean = false): Flowable<MutableList<CurrencyEntity>>

    @Query("SELECT * FROM $CURRENCY_TABLE_NAME WHERE monitored = :B")
    fun selectMonitored(B: Boolean = true): Flowable<MutableList<CurrencyEntity>>

    @Query("UPDATE $CURRENCY_TABLE_NAME SET  monitored =:isMonitore WHERE id=:inId")
    fun updateMonitored(isMonitore: Boolean, inId: Long): Single<Int>
}