package nkanyiso.hlela.com.mycurreny.data.db

interface RoomDbCallbackListener<T> {
    fun onSelectBulk(result: MutableList<T>){}
    fun onUpdated(result: Boolean){}
}