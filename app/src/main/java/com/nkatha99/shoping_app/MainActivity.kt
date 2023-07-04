package com.nkatha99.shoping_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nkatha99.shoping_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var productsAdapter: ProductsAdapter
    var itemProduct :List<Product> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productsAdapter = ProductsAdapter(emptyList())
        binding.rvProducts.adapter = productsAdapter
    }

    override fun onResume() {
        super.onResume()
        getProducts()
    }

    fun getProducts(){
        val apiclient = ApiClient.buildClient(ApiInterface::class.java)
        val request = apiclient.getProducts()
        request.enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                if(response.isSuccessful){
                    var products = response.body()?.products

                    var productsAdapter=ProductsAdapter(products?: emptyList())
                    binding.rvProducts.layoutManager= LinearLayoutManager(this@MainActivity)
                    binding.rvProducts.adapter=productsAdapter




                    Toast.makeText(baseContext, "fetched ${products?.size} products", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}