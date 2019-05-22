package nkanyiso.hlela.com.mycurreny.ui.startup

import android.R.*
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.currency_item.view.*
import nkanyiso.hlela.com.mycurreny.R
import nkanyiso.hlela.com.mycurreny.data.db.entity.CurrencyEntity


class CurrencyAdapter(private var items: MutableList<CurrencyEntity>,private var itemClick: ItemClick) :
    androidx.recyclerview.widget.RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = from(parent.context).inflate(R.layout.currency_item, parent, false)

        return ViewHolder(view = view)
    }

    fun refreshItemsData(newItems: MutableList<CurrencyEntity>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {

        val dataItem = items[position]

        viewholder.view.name.text = dataItem.name
        viewholder.view.desription.text = dataItem.description
        viewholder.view.image_view_button.setOnClickListener {
            itemClick.setClick(dataItem)
        }

        if (dataItem.monitored) viewholder.view.image_view_button.setImageResource(drawable.ic_menu_delete) else {
            viewholder.view.image_view_button.setImageResource(drawable.ic_input_add)
        }


    }

    interface ItemClick {
        fun setClick(currencyEntity: CurrencyEntity)

    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].id
    }

    inner class ViewHolder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}