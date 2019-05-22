package nkanyiso.hlela.com.mycurreny.ui.currencyConverter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nkanyiso.hlela.com.mycurreny.R


class CurrencyConverterFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.activity!!.title = getString(R.string.title_convert_currency)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.currency_converter_fragment, container, false)
    }


}
