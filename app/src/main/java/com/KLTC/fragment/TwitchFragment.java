package com.KLTC.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.KLTC.R;

public class TwitchFragment extends Fragment {

    WebView mWebView;

    public TwitchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TwitchFragment newInstance(String param1, String param2) {
        TwitchFragment fragment = new TwitchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitch, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //video url
        String videoUrl = this.getArguments().getString("message");
        //initialize webview
        mWebView =requireView().findViewById(R.id.TwitchWebview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.getMediaPlaybackRequiresUserGesture();

        // Load the Twitch Stream you want
        // Actually, this is the most important line if code
        // The "embed" searches for the suitable stream format
        mWebView.loadUrl(videoUrl);
        Log.e("tag", videoUrl);


    }
}