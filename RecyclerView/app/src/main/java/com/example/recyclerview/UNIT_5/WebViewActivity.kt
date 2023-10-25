package com.example.recyclerview.UNIT_5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.recyclerview.R

class WebViewActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webView: WebView = findViewById(R.id.webView)
        val customHTML = "<html><body><h1>DEMO</h1>" +
                "<h2>Hello World</h2><h3>Hello World</h3>" +
                "<p>Static HTML Content.</p>"+
                "</body>" +
                "<script>alert(\"Warning\");</script></html>"
        webView.loadData(customHTML, "text/html","UTF-8")
    }
}