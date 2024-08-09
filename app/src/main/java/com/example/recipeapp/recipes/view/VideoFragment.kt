package com.example.recipeapp.recipes.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import java.lang.Exception

class VideoFragment : Fragment() {

    private val args: VideoFragmentArgs by navArgs()
    private lateinit var videoView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val url = args.url
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        videoView = view.findViewById(R.id.webView)
        setupWebView()
        playYouTubeVideo(url)
        return view

    }

    private fun setupWebView() {
        videoView.settings.javaScriptEnabled = true
        videoView.settings.domStorageEnabled = true
        videoView.webViewClient = WebViewClient()  // Ensures that links open in the WebView
    }
    private fun playYouTubeVideo(url: String) {
        videoView.loadUrl(url)
    }


}