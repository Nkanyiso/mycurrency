package nkanyiso.hlela.com.mycurreny.ui.startup.viewmodel

import androidx.lifecycle.MutableLiveData
import nkanyiso.hlela.com.mycurreny.data.db.RoomDbCallbackListener
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity
import nkanyiso.hlela.com.mycurreny.data.db.repo.StartupRepo
import nkanyiso.hlela.com.mycurreny.ui.BaseViewModel

public class StartViewModel() : BaseViewModel(),RoomDbCallbackListener<CurrencyEntity>{

    var repository:  StartupRepo = StartupRepo()
    override fun onSelectBulk(result: MutableList<CurrencyEntity>) {
        currencyMonitoredLiveData.postValue(result)
    }

    val currencyMonitoredLiveData : MutableLiveData<MutableList<CurrencyEntity>> by lazy {
        MutableLiveData<MutableList<CurrencyEntity>>()
    }


    fun getMonitored(){

        mCompositeDisposable.add(repository.getMonitored(this))
    }
    fun updateMonitoredState(id:Long,isMonitored:Boolean){
        mCompositeDisposable.add(repository.removeFromMonitored(isMonitored,id){
            getMonitored()
        })

    }

}