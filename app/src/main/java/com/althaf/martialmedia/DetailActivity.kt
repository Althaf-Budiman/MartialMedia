package com.althaf.martialmedia

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.althaf.martialmedia.data.ArticlesItem
import com.althaf.martialmedia.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra(EXTRA_DATA, ArticlesItem::class.java)

        val date = data?.publishedAt?.take(10)
        val dateArray = date?.split("-")?.toTypedArray()

        val time = data?.publishedAt?.takeLast(9)
        val timeArray = time?.split(":")?.toTypedArray()

        val calendar = Calendar.getInstance()
        dateArray?.let {
            calendar.set(Calendar.YEAR, it[0].toInt())
            calendar.set(Calendar.MONTH, it[1].toInt() - 1)
            calendar.set(Calendar.DATE, it[2].toInt())
        }
        timeArray?.let {
            calendar.set(Calendar.HOUR, it[0].toInt())
            calendar.set(Calendar.MINUTE, it[1].toInt())
        }

        val dateFormat = SimpleDateFormat("MMM, dd ''yy", Locale.getDefault()).format(calendar.time)
        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault()).format(calendar.time)

        val publisedResult = "$dateFormat at $timeFormat"

        binding.apply {
            detailTitle.text = data?.title
            detailAuthor.text = data?.author
            detailDate.text = publisedResult
            Picasso.get().load(data?.urlToImage).into(detailImage)
            setWebView(data)
        }
    }

    private fun setWebView(data: ArticlesItem?) {
        var loadingFinished = true
        var redirect = false

        binding.wvDetail.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (!loadingFinished) {
                    redirect = true
                }
                loadingFinished = false
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loadingFinished = false
                binding.loadingView.root.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                if (!redirect) {
                    loadingFinished = true
                }

                if (loadingFinished && !redirect) {
                    binding.loadingView.root.visibility = View.GONE
                } else {
                    redirect = false
                }
            }
        }
        data?.url?.let { binding.wvDetail.loadUrl(it) }
    }

    companion object  {
        const val EXTRA_DATA = "data"
    }
}