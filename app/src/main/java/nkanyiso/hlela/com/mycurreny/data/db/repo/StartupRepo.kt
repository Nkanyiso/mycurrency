package nkanyiso.hlela.com.mycurreny.data.db.repo

import android.app.Application
import android.app.SharedElementCallback
import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nkanyiso.hlela.com.mycurreny.MyApplication
import nkanyiso.hlela.com.mycurreny.data.db.CurrencyDatabase
import nkanyiso.hlela.com.mycurreny.data.db.RoomDbCallbackListener
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity

class StartupRepo() {
    val db = MyApplication.getDatabase()

    fun getMonitored(callback: RoomDbCallbackListener<CurrencyEntity>): Disposable {
        return db!!.provideCurrency().selectMonitored().subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    callback.onSelectBulk(result)
                },

                Throwable::printStackTrace
            )
    }

    fun removeFromMonitored(
        isMonitored: Boolean,
        scheduleId: Long,
        callback: (Boolean) -> Unit
    ): Disposable {
        return db!!.provideCurrency().updateMonitored(isMonitored, scheduleId).subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->

                    callback(result > 0)


                },

                Throwable::printStackTrace
            )
    }


}
