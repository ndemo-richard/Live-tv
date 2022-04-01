package com.KLTC.fragment;

import android.graphics.Color;
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
import android.widget.ImageView;

import com.KLTC.R;

public class RadioFragment extends Fragment {

    WebView mWebView;

    public RadioFragment() {
    }
    public static RadioFragment newInstance(String param1, String param2) {
        RadioFragment fragment = new RadioFragment();
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
        return inflater.inflate(R.layout.fragment_radio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String radioUrl = this.getArguments().getString("message");
        //initialize webview
        mWebView =requireView().findViewById(R.id.radioWebview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.getMediaPlaybackRequiresUserGesture();
        mWebView.setBackgroundColor(0);
        mWebView.loadUrl(radioUrl);
    }

    @Override
    public void onStop() {
        super.onStop();
        mWebView.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mWebView.onPause();


    }
}