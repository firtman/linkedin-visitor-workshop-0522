package com.linkedin.billing.livisitor.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.Toast
import com.linkedin.billing.livisitor.R
import com.linkedin.billing.livisitor.databinding.ActivityInfoBinding
import com.linkedin.billing.livisitor.databinding.ActivityNoteAddBinding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var jsInterface = object {
            @JavascriptInterface
            fun showToast() {

            }
        }

        binding.webview.addJavascriptInterface(jsInterface, "kotlin")

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = object: WebViewClient() {

        }
        binding.webview.webChromeClient = object: WebChromeClient() {
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                Toast.makeText(this@InfoActivity, message, Toast.LENGTH_LONG).show()
                return super.onJsAlert(view, url, message, result)
            }
        }

        binding.btnHTML.setOnClickListener {
        val html = """
            <h1>Hey, I'm HTML</h1>
            <p>Sure?</p>
        """.trimIndent()
            binding.webview.loadData(html, "text/html",
                "utf-8")
        }
        binding.btnFile.setOnClickListener {
            binding.webview.loadUrl("file:///android_asset/index.html")
        }
        binding.btnURL.setOnClickListener {
            binding.webview.loadUrl("https://linkedin.com")
        }
        binding.btnJS.setOnClickListener {
            binding.webview.evaluateJavascript(
                "alert('hello from kotlin')") {

                }
        }
    }
}