package com.example.demoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    private val Url = "https://fakestoreapi.com/"
    private val TAG: String = "CHECK_RESPONSE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerview)

        getAllData()}
    private fun getAllData() {
        val api = Retrofit.Builder()
            .baseUrl(Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        api.getData().enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (item in it) {

                            Log.i(TAG, "onResponse : ${item.title}")
                            myAdapter= MyAdapter(this@MainActivity, it )
                            recyclerView.adapter=myAdapter
                            recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                        }
                    }
                } else {
                    Log.i(TAG, "onResponse: Failure - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.i(TAG, "onFailure : ${t.message}")
            }
        })
    }
}