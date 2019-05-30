package nkanyiso.hlela.com.mycurreny.ui.addcurrency.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity
import nkanyiso.hlela.com.mycurreny.data.db.repo.AddCurrencyRepository

class AddCurrencyViewModel(application: Application) : AndroidViewModel(application) {


    val currencyLiveData: MutableLiveData<MutableList<CurrencyEntity>> by lazy {
        MutableLiveData<MutableList<CurrencyEntity>>()
    }
    var repository: AddCurrencyRepository = AddCurrencyRepository()
    fun fetchAllCurrencies() {
        repository.getAllCurrencies {
            if(it){
            println("Found countries")
            } else{
                println("No countries found")
            }
            updateCurrencyLiveData()
        }

    }

    fun updateCurrencyLiveData() {

        repository.updateCurrencyLiveData {
            currencyLiveData.postValue(it)
        }
    }

    fun updateMonitoredState(id: Long, isMonitored: Boolean) {
         repository.addFromMonitored(isMonitored,id){
            updateCurrencyLiveData()
        }

    }


}