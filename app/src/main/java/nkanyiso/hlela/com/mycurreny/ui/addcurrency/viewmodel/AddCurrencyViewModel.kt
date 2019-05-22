package nkanyiso.hlela.com.mycurreny.ui.addcurrency.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nkanyiso.hlela.com.mycurreny.data.db.CurrencyDatabase
import nkanyiso.hlela.com.mycurreny.data.db.dao.CurrencyDao
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity
import nkanyiso.hlela.com.mycurreny.data.retrofit.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class AddCurrencyViewModel(application: Application) : AndroidViewModel(application) {
    val db = CurrencyDatabase.getInstance(application)

    val currencyLiveData: MutableLiveData<MutableList<CurrencyEntity>> by lazy {
        MutableLiveData<MutableList<CurrencyEntity>>()
    }

    fun fetchAllCurrencies() {
        val service = RetrofitFactory.makeRetrofitService()
        val call = service.getCurrencies()

        call.enqueue(object : Callback<HashMap<String, String>> {
            override fun onResponse(call: Call<HashMap<String, String>>, response: Response<HashMap<String, String>>) {
                if (response.code() == 200) {
                    val mCurrencyList = mutableListOf<CurrencyEntity>()

                    for (entry in response.body()?.entries!!) {

                        val mCurrency = CurrencyEntity(name = entry.key, description = entry.value)

                        println("Item : " + entry.key + " Count : " + entry.value)

                        mCurrencyList.add(mCurrency)
                    }

                    val newArr = mCurrencyList.toTypedArray()

                    db.provideCurrency().insertAll(*newArr)
                    updateCurrencyLiveData()
                }
            }

            override fun onFailure(call: Call<HashMap<String, String>>, t: Throwable) {
            }
        })
    }

    fun updateCurrencyLiveData() {
        val nonMonitoredList = db.provideCurrency().select()
        // if (nonMonitoredList.isNotEmpty()) {
        currencyLiveData.postValue(nonMonitoredList)
        //}
    }

    fun updateMonitoredState(id: Long, isMonitored: Boolean) {
        db.provideCurrency().updateMonitored(isMonitored, id)
        updateCurrencyLiveData()
    }


}