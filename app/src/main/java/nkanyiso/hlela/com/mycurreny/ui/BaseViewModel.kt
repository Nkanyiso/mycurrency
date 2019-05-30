package nkanyiso.hlela.com.mycurreny.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

public open class BaseViewModel (): ViewModel(){

    val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
        super.onCleared()
    }
}