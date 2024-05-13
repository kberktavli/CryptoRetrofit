package com.berktavli.cryptoretrofit.retrofit

import android.telecom.Call
import com.berktavli.cryptoretrofit.data.entity.CryptoModel
import retrofit2.http.GET

interface CryptoAPI {
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): retrofit2.Call<List<CryptoModel>>
}