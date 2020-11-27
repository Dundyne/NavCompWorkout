package com.example.workoutapp.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.workoutapp.R;
import com.example.workoutapp.databinding.ActivityResourceBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;




public class ResourceActivity extends YouTubeBaseActivity {

    YouTubePlayerView youtubeView;
    ImageButton btnYoutube;
    YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        btnYoutube = (ImageButton) findViewById(R.id.btnYoutube);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("youtubetag", "Youtube video er ferdig lastet inn.");
                youTubePlayer.loadVideo("wWGulLAa0O0");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("youtubefailtag", "Something went wrong..");
            }
        };

        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                youtubeView.initialize(YoutubeConfig.getApiKey(), onInitializedListener );
            }
        });


    }
}