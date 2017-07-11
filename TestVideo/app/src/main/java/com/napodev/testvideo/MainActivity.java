package com.napodev.testvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by opannapo on 7/12/17.
 */
public class MainActivity extends AppCompatActivity {
    SimpleExoPlayer simpleExoPlayer;
    TrackSelector trackSelector;

    SimpleExoPlayerView exoPlayerView;
    TextView tKet;
    RelativeLayout layControl;
    ImageButton btnPlay;
    ImageButton btnPause;

    boolean isPlaying;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);

        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.player_view);
        tKet = (TextView) findViewById(R.id.tKet);
        layControl = (RelativeLayout) findViewById(R.id.layControl);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnPause = (ImageButton) findViewById(R.id.btnPause);

        VideoUtils videoUtils = new VideoUtils(this);
        videoUtils.type(VideoUtils.VIDEO_TYPE.HLS)
                .url(Link.HLS.url1)
                .buildBandwidthMeter()
                .buildMediaSource();
        /*videoUtils.type(VideoUtils.VIDEO_TYPE.DASH)
                .url(Link.DASH.url1)
                .buildBandwidthMeter()
                .buildMediaSource();*/
        /*videoUtils.type(VideoUtils.VIDEO_TYPE.MP4)
                .url(Link.MP4.url1)
                .buildBandwidthMeter()
                .buildMediaSource();*/

        trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(videoUtils.getBandwidthMeter()));
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        simpleExoPlayer.prepare(videoUtils.getMediaSource());
        exoPlayerView.setPlayer(simpleExoPlayer);
        tKet.setText(videoUtils.getTypeInfo());
        simpleExoPlayer.setPlayWhenReady(true);


        layControl.setAlpha(0.0f);
        layControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layControl.setAlpha(1.0f);
                isPlaying = simpleExoPlayer.getPlayWhenReady();

                if (isPlaying) {
                    btnPlay.setVisibility(View.GONE);
                    btnPause.setVisibility(View.VISIBLE);
                    simpleExoPlayer.setPlayWhenReady(!simpleExoPlayer.getPlayWhenReady());
                    layControl.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnPlay.setVisibility(View.VISIBLE);
                            btnPause.setVisibility(View.GONE);
                        }
                    }, 500);
                } else {
                    simpleExoPlayer.setPlayWhenReady(true);
                    layControl.setAlpha(0.0f);
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if (simpleExoPlayer != null) {
                simpleExoPlayer.release();
                simpleExoPlayer = null;
                trackSelector = null;
            }
        }
    }

}
