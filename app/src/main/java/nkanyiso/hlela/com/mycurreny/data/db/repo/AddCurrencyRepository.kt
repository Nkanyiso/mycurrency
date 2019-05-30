package nkanyiso.hlela.com.mycurreny.data.db.repo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nkanyiso.hlela.com.mycurreny.MyApplication
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity
import nkanyiso.hlela.com.mycurreny.data.retrofit.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class AddCurrencyRepository {
    val db = MyApplication.getDatabase()

    fun updateCurrencyLiveData(callback: (MutableList<CurrencyEntity>) -> Unit): Disposable {
        return db!!.provideCurrency().select().subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    callback(result)
                },

                Throwable::printStackTrace
            )

    }

    fun getAllCurrencies(callback: (Boolean) -> Unit) {
        val service = RetrofitFactory.makeRetrofitService()
        val call = service.getCurrencies()
       // return callback(false);
        call.enqueue(object : Callback<HashMap<String, String>> {
            override fun onResponse(call: Call<HashMap<String, String>>, response: Response<HashMap<String, String>>) {
                if (response.code() == 200) {
                    val mCurrencyList = mutableListOf<CurrencyEntity>()

                    for (entry in response.body()?.entries!!) {

                        val mCurrency = CurrencyEntity(name = entry.key, description = entry.value)

                        mCurrencyList.add(mCurrency)
                    }
                    val newArr = mCurrencyList.toTypedArray()

                    db!!.provideCurrency().insertAll(*newArr)
                   return callback(true);
                }
                return callback(false);

            }
            override fun onFailure(call: Call<HashMap<String, String>>, t: Throwable) {
                return callback(false);
            }
        })
    }
    fun addFromMonitored(
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