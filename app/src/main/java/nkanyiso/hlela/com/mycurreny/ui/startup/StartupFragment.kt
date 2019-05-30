package nkanyiso.hlela.com.mycurreny.ui.startup


import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.startup_fragment.view.*
import nkanyiso.hlela.com.mycurreny.R
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity
import nkanyiso.hlela.com.mycurreny.ui.startup.viewmodel.StartViewModel


class StartupFragment : androidx.fragment.app.Fragment(), CurrencyAdapter.ItemClick {
    override fun setClick(currencyEntity: CurrencyEntity) {
        viewModel.updateMonitoredState(currencyEntity.id, false)
    }

    lateinit var viewModel: StartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var that = this
        this.activity!!.title = getString(R.string.title_monitored)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.startup_fragment, container, false)




        viewModel = ViewModelProviders.of(this).get(StartViewModel()::class.java)

        val currencyAdapter = CurrencyAdapter(mutableListOf(), that)
        view.recycler_monitored?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = currencyAdapter
        }

        view.add_currency.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_Startup_to_actionAddCurrency)
        }

        viewModel.getMonitored()
        viewModel.currencyMonitoredLiveData.observe(this, object : Observer<MutableList<CurrencyEntity>> {
            override fun onChanged(t: MutableList<CurrencyEntity>?) {
                if (t != null) {
                    if (t.size == 0) {//show  no currencies
                        view.non_monitored.visibility = VISIBLE
                        view.recycler_monitored.visibility = GONE
                    } else {
                        view.non_monitored.visibility = GONE
                        if (view.recycler_monitored.visibility == GONE) {
                            view.recycler_monitored.visibility = VISIBLE
                        }
                        currencyAdapter.refreshItemsData(t)
                    }

                }
            }
        })
        return view
    }
}
