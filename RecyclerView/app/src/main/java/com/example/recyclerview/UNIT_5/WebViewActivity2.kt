package com.example.recyclerview.UNIT_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import com.example.recyclerview.R

class WebViewActivity2 : AppCompatActivity() {
    lateinit var webView2: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view2)
        webView2 = findViewById(R.id.webView2)
        val webSettings: WebSettings = webView2.getSettings()
        webSettings.javaScriptEnabled = true
        webView2.loadUrl("https://tryhackme.com/")
    }
}