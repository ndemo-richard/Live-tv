package com.livetv.player

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.KLTC.R
import com.dailymotion.android.player.sdk.PlayerWebView

class AndroidPlayer(private val mContext: Context) : AppCompatActivity(){
    private var currUri: Uri? = null
    private var playbackPosition: Long = 0
    private var currentWindowIndex = 0
    var playWhenReady = true


    lateinit var playerWebView: PlayerWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily_motion_api);

        playerWebView = findViewById(R.id.dm_player_web_view)
        val params = mapOf("video" to "x26hv6c")
        playerWebView.load( params);
    }
}