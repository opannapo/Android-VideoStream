package com.napodev.testvideo;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;

import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by opannapo on 7/11/17.
 */
public class VideoUtils {
    private VIDEO_TYPE videoType;
    private Uri uri;
    private MediaSource mediaSource;
    private DataSource.Factory dataSourceFactory;
    private BandwidthMeter bandwidthMeter;
    private Context context;

    private Handler mainHandler = null;

    public enum VIDEO_TYPE {
        DASH,
        MP4,
        HLS
    }

    public VideoUtils(Context context) {
        this.context = context;
        dataSourceFactory = new DefaultDataSourceFactory(this.context, Util.getUserAgent(this.context, "ExoPlayer"));
    }

    public VideoUtils type(VIDEO_TYPE type) {
        this.videoType = type;
        return this;
    }

    public VideoUtils url(String url) {
        this.uri = Uri.parse(url);
        return this;
    }

    public VideoUtils buildBandwidthMeter() {
        bandwidthMeter = new DefaultBandwidthMeter();
        return this;
    }

    public VideoUtils buildMediaSource() {
        if (videoType == VIDEO_TYPE.DASH) {
            mediaSource = new DashMediaSource(uri, dataSourceFactory, new DefaultDashChunkSource.Factory(dataSourceFactory), null, null);
        } else if (videoType == VIDEO_TYPE.HLS) {
            mainHandler = new Handler();
            mediaSource = new HlsMediaSource(uri, dataSourceFactory, mainHandler, null);
        } else if (videoType == VIDEO_TYPE.MP4) {
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            mediaSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);
        }

        return this;
    }

    public MediaSource getMediaSource() {
        return mediaSource;
    }

    public BandwidthMeter getBandwidthMeter() {
        return bandwidthMeter;
    }

    public String getTypeInfo() {
        return "VideoType :: " + videoType.toString() + "\n" + uri;
    }

}
