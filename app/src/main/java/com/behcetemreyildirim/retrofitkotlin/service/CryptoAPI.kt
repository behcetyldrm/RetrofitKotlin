package com.behcetemreyildirim.retrofitkotlin.service

import com.behcetemreyildirim.retrofitkotlin.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //https://raw.githubusercontent.com/ -> Site adresini view kısmında retrofit ile kullanaacğız
    //atilsamancioglu/K21-JSONDataSet/master/crypto.json -> kalan uzantıyı ise interface'de alacağız

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Observable<List<CryptoModel>>
    //observable -> gözlemlenebilir anlamına gelir yani verideki değişikliğe göre çalışır. Rxjava ögesi

    //fun getData(): Call<List<CryptoModel>>  //retrofit ögesi

    /*retrofit'in get komutu ile neredeki verinin alınacağını belirttik. sitenin adresini view kısmında kullanacağız.
    daha sonra onu interface ile bağlayacağız.

    fonksiyonuz ile GET ile belirttiğimiz sitedeki verileri alıp cryptoModel sınıfına işliyoruz
    */


}