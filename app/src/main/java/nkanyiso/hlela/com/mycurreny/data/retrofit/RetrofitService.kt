package nkanyiso.hlela.com.mycurreny.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import java.util.HashMap

interface RetrofitService {

    @GET("currencies.json")
    fun getCurrencies(): Call<HashMap<String, String>>
}
