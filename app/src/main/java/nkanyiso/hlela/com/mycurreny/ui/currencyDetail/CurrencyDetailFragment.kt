package nkanyiso.hlela.com.mycurreny.ui.currencyDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nkanyiso.hlela.com.mycurreny.R


class CurrencyDetailFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.activity!!.title = getString(R.string.title_currency_detail)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.currency_detail_fragment, container, false)
    }


}
