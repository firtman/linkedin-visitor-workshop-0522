package com.linkedin.billing.livisitor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.linkedin.billing.livisitor.R
import com.linkedin.billing.livisitor.data.WeatherAPI
import com.linkedin.billing.livisitor.databinding.ActivityNotesBinding
import com.linkedin.billing.livisitor.databinding.ActivityVisitorBinding

class VisitorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVisitorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVisitorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTemperature.text = "⏳"
        WeatherAPI.request(this) { response->
            if (response.ok) {
                binding.txtTemperature.text = "${response.temperature}⁰ F"
            } else {
                binding.txtTemperature.text = "Error :("
            }
        }
    }
}