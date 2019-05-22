package nkanyiso.hlela.com.mycurreny.ui.startup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import nkanyiso.hlela.com.mycurreny.data.db.CurrencyDatabase
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity

class StartViewModel(application: Application) : AndroidViewModel(application){
    val db = CurrencyDatabase.getInstance(application.applicationContext)
    val currencyMonitoredLiveData : MutableLiveData<MutableList<CurrencyEntity>> by lazy {
        MutableLiveData<MutableList<CurrencyEntity>>()
    }
    fun getMonitored(){
        val monitoredList=db.provideCurrency().selectMonitored()
            currencyMonitoredLiveData.postValue(monitoredList)
    }
    fun updateMonitoredState(id:Long,isMonitored:Boolean){
        db.provideCurrency().updateMonitored(isMonitored,id)
        getMonitored()
    }

}