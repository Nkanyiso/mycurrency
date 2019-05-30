package nkanyiso.hlela.com.mycurreny

import android.app.Application
import com.facebook.stetho.Stetho
import nkanyiso.hlela.com.mycurreny.data.db.CurrencyDatabase
import nkanyiso.hlela.com.mycurreny.data.db.CurrencyDatabase.Companion.getInstance


class MyApplication : Application() {
    public lateinit var appInstance: MyApplication

    companion object {
        var db: CurrencyDatabase? = null
        fun getDatabase(): CurrencyDatabase? {
            return db
        }

    }
    override fun onCreate() {
        super.onCreate()
        appInstance = this;
        Stetho.initializeWithDefaults(this)
        db= getInstance(applicationContext)
    }

}