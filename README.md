# Google/ExoPlayer Example
  

### Dependencies used by This Sample
| Name | Type |
| ------ | ------ |
|compile com.github.opannapo:Bework:Beta-1.0 | Dependencies 
|compile com.android.support:appcompat-v7:25.0.+ | Dependencies 
|compile com.google.android.exoplayer:exoplayer-core:r2.4.0 | Dependencies 
|compile com.google.android.exoplayer:exoplayer-dash:r2.4.0 | Dependencies 
|compile com.google.android.exoplayer:exoplayer-hls:r2.4.0 | Dependencies 
|compile com.google.android.exoplayer:exoplayer-smoothstreaming:r2.4.0 | Dependencies 
|compile com.google.android.exoplayer:exoplayer-ui:r2.4.0 | Dependencies 
 

# How to use
  
### 1. init HLS video.
```java
...
VideoUtils videoUtils = new VideoUtils(this); 
videoUtils.type(VideoUtils.VIDEO_TYPE.HLS)
        .url(yourStringUrl)
        .buildBandwidthMeter()
        .buildMediaSource();
...  
``` 

### 2. init DASH video.
```java
...
VideoUtils videoUtils = new VideoUtils(this); 
videoUtils.type(VideoUtils.VIDEO_TYPE.DASH)
        .url(yourStringUrl)
        .buildBandwidthMeter()
        .buildMediaSource();
...  
``` 

### 3. init MP4 video.
```java
...
VideoUtils videoUtils = new VideoUtils(this); 
videoUtils.type(VideoUtils.VIDEO_TYPE.MP4)
        .url(yourStringUrl)
        .buildBandwidthMeter()
        .buildMediaSource();
...  
``` 

### 4. Implementation.
```java
...
trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(videoUtils.getBandwidthMeter()));
simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

simpleExoPlayer.prepare(videoUtils.getMediaSource());
exoPlayerView.setPlayer(simpleExoPlayer);
...  
``` 





 