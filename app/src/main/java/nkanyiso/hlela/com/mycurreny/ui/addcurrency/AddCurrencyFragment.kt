package nkanyiso.hlela.com.mycurreny.ui.addcurrency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.add_currency_fragment.view.*
import nkanyiso.hlela.com.mycurreny.R
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity
import nkanyiso.hlela.com.mycurreny.ui.addcurrency.viewmodel.AddCurrencyViewModel
import nkanyiso.hlela.com.mycurreny.ui.startup.CurrencyAdapter

class AddCurrencyFragment : androidx.fragment.app.Fragment(), CurrencyAdapter.ItemClick {
    override fun setClick(currencyEntity: CurrencyEntity) {
        viewModel.updateMonitoredState(currencyEntity.id, true)

    }

    lateinit var viewModel: AddCurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.activity!!.title = getString(R.string.title_add_currency)
        val view = inflater.inflate(R.layout.add_currency_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(AddCurrencyViewModel::class.java)
        viewModel.fetchAllCurrencies()
        val currencyAdapter = CurrencyAdapter(mutableListOf(), this)

        view.recycler_currencies.apply {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }

        viewModel.currencyLiveData.observe(this, object : Observer<MutableList<CurrencyEntity>> {
            override fun onChanged(t: MutableList<CurrencyEntity>?) {
                if (t != null) {

                    if (t.size == 0) {//show  no currencies
                        view.non_currency.visibility = View.VISIBLE
                        view.recycler_currencies.visibility = View.GONE
                    } else {
                        view.non_currency.visibility = View.GONE
                        if (view.recycler_currencies.visibility == View.GONE) {
                            view.recycler_currencies.visibility = View.VISIBLE
                        }
                        currencyAdapter.refreshItemsData(t)
                    }
                }
            }
        })

        return view

    }
}
