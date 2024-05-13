package com.berktavli.cryptoretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.berktavli.cryptoretrofit.R
import com.berktavli.cryptoretrofit.data.entity.CryptoModel
import com.berktavli.cryptoretrofit.databinding.ActivityMainBinding
import com.berktavli.cryptoretrofit.retrofit.CryptoAPI
import com.berktavli.cryptoretrofit.ui.adapter.CryptoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CryptoAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.RV.layoutManager = LinearLayoutManager(this)
        loadData()

    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()
        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModels = ArrayList(it)
                        cryptoModels?.let {
                            val adapter = CryptoAdapter(it,this@MainActivity)
                            binding.RV.adapter = adapter
                        }

                       /* for (cryptoModel: CryptoModel in cryptoModels!!) {
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }
                        */
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked :  + ${cryptoModel.currency}", Toast.LENGTH_LONG).show()
    }
}