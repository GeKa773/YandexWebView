package com.geka.radchenko.yandexwebview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geka.radchenko.yandexwebview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val viewModel by lazy { ViewModelProvider(this)[AppViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initWebView()

    }

    companion object {
        const val MAPS = "https://yandex.ru/maps"
        const val WEATHER = "https://yandex.ru/pogoda"
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding!!.webView.run {
            loadUrl(viewModel.getLastUrl())
            clearCache(true)
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        }

        binding!!.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                url?.let {
                    Log.i("TTT", "url  =  $it")
                    viewModel.saveLastUrl(it)

                    if (MAPS in it) {
                        openMaps()
                    }
                    if (WEATHER in it) {
                        openWeather()
                    }
                }
                super.onPageStarted(view, url, favicon)
            }
        }
    }

    private fun openMaps() {
        try {
            val url = "yandexmaps://maps.yandex.ru"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {}
    }

    private fun openWeather() {
        try {
            val url = "????????????????????"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {}
    }

    override fun onBackPressed() {
        if (binding != null) {
            with(binding!!) {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    BackPressedDialog {
                        super.onBackPressed()
                    }.show(supportFragmentManager, "Tag")
                }
            }
        } else {
            super.onBackPressed()
        }
    }
}