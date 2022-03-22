package com.KLTC.player;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.KLTC.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class AndroidPlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

   public static final String API_KEY = "API_KEY";
   public static final String VIDEO_ID = "VIDEO_ID";

   private YouTubePlayer youTubePlayer;
   private YouTubePlayerFragment youTubePlayerFragment;
   private TextView textVideoLog;
   private Button btnViewFullScreen;


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}