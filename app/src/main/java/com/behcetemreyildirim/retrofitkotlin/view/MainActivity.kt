package com.behcetemreyildirim.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.behcetemreyildirim.retrofitkotlin.R
import com.behcetemreyildirim.retrofitkotlin.adapter.RecyclerAdapter
import com.behcetemreyildirim.retrofitkotlin.databinding.ActivityMainBinding
import com.behcetemreyildirim.retrofitkotlin.model.CryptoModel
import com.behcetemreyildirim.retrofitkotlin.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    var recyclerViewAdapter: RecyclerAdapter? = null

    //Disposable -> tek kullanımlık anlamına gelir. yani çekilen veriyi kullandıktan sonra bellekten sileriz
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        compositeDisposable = CompositeDisposable()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager


        loadData()
    }

    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) //verilerin alınacağı ana sayfa url'si
            .addConverterFactory(GsonConverterFactory.create()) //gelen json verisini model sınıfımıza dönüştürür
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //rxjava'yı kullanmak için
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(retrofit.getData()//verinin bulunduğu yere gittik. (sununu veya web sitesi)
            .subscribeOn(Schedulers.io()) //veriyi gözlemledik
            .observeOn(AndroidSchedulers.mainThread()) //gözlemlenen veriyi belleğe işledik
            .subscribe(this::handleResponse)) //işlemi tamamladık ve alınan verileri kullanmak için fonksiyona ilettik


        //Call servisi ile kullanım. Retrofit ögesi
        /*
        val service = retrofit.create(CryptoAPI::class.java) //retrofit ile interface'i bağladık
        val call = service.getData() //interface'deki fonksiyonumuz ile verileri aldık

        call.enqueue(object: Callback<List<CryptoModel>>{ //verileri asenkron şekilde almamızı sağlar.
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                //cevap gelirse ne yapılacak
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        cryptoModels?.let {
                            recyclerViewAdapter = RecyclerAdapter(cryptoModels!!)
                            binding.recyclerView.adapter = recyclerViewAdapter
                        }

                        /*for (cryptoModel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }*/
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                //hata gelirse ne yapılacak
                t.printStackTrace()
            }

        })
        */
    }

    private fun handleResponse(cryptoList: List<CryptoModel>){
        cryptoModels = ArrayList(cryptoList)

        cryptoModels?.let {
            recyclerViewAdapter = RecyclerAdapter(cryptoModels!!)
            binding.recyclerView.adapter = recyclerViewAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable?.clear()
    //rxjava'nın disposable özelliğini bu yüzden kullanıyoruz. sayfa silindikten sonra bütün veriler temizlenir ve bellek boşalır
    }
}