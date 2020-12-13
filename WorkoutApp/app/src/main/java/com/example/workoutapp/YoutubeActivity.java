package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.workoutapp.databinding.ActivityYoutubeBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.example.workoutapp.YoutubeConfig.*;


public class YoutubeActivity extends YouTubeBaseActivity {
    ActivityYoutubeBinding binding;
    YouTubePlayerView youtubeView;
    Button btnYoutube;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYoutubeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        youtubeView = binding.youtubeView;
        btnYoutube = binding.btnYoutube;

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

                youtubeView.initialize(getApiKey(), onInitializedListener );
            }
        });

    }
}