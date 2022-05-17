package com.linkedin.billing.livisitor.data

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

data class WeatherData(var ok: Boolean, var temperature: Double?)

class WeatherAPI {

    companion object {
        fun request(context: Context, callback: (WeatherData) -> Unit) {
            val queue = Volley.newRequestQueue(context)
            val url =
                "https://api.openweathermap.org/data/2.5/weather?q=Mountain+View&appid=85ad76cb241ddd400e9c40d1b59c5f74&units=imperial"
            val jsonRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    val temperature = response.getJSONObject("main").getDouble("temp")
                    callback(WeatherData(true, temperature))
                },
                {
                    callback(WeatherData(false, null))
                }
            )
            // Add the request to the RequestQueue.
            queue.add(jsonRequest)
        }
    }
}