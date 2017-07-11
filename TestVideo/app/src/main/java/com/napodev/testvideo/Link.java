package com.napodev.testvideo;

/**
 * Created by opannapo on 7/12/17.
 */
public final class Link {
    public static final class DASH {
        public static final String url1 = "https://bitmovin-a.akamaihd.net/content/playhouse-vr/mpds/105560.mpd"; //BISA DASH
        public static final String url2 = "http://www.bok.net/dash/tears_of_steel/cleartext/stream.mpd"; //BISA DASH Multi Bitrate
        public static final String url3 = "http://amssamples.streaming.mediaservices.windows.net/683f7e47-bd83-4427-b0a3-26a6c4547782/BigBuckBunny.ism/manifest(format=mpd-time-csf)"; //BISA DASH
    }

    public static final class HLS {
        public static final String url1 = "https://mnmedias.api.telequebec.tv/m3u8/29880.m3u8";//BISA HLS
        public static final String url2 = "https://bitmovin-a.akamaihd.net/content/playhouse-vr/m3u8s/105560.m3u8";//BISA HLS
    }

    public static final class MP4 {
        public static final String url1 = "http://techslides.com/demos/sample-videos/small.mp4"; //BISA MEDIASOURCE mp4
        public static final String url2 = "https://www.rmp-streaming.com/media/bbb-360p.mp4"; //BISA mp4
    }
}
