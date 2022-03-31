
//this is working
package com.KLTC.fragment;

import static com.KLTC.remote.DevKey.DEVELOPER_KEY;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.KLTC.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;

public class YoutubeFragment extends Fragment {

    private static final String API_KEY = DEVELOPER_KEY;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.you_tube_api, container, false);
        //video id
        String videoID = this.getArguments().getString("message");

        YouTubePlayerSupportFragmentX youTubePlayerFragment = YouTubePlayerSupportFragmentX.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {

                if(!b){
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                   // player.cueVideo(videoID);
                    player.loadVideo(videoID);
                    player.play();

                }

            }


            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                 //youtube error
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage", errorMessage);

            }
        });


        return rootView;

    }


}