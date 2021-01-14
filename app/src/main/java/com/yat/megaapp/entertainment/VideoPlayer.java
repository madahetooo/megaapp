package com.yat.megaapp.entertainment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.yat.megaapp.R;

public class VideoPlayer extends Fragment {

    PlayerView videoExoPlayer;
    SimpleExoPlayer simpleExoPlayer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_video_player, container, false);
        videoExoPlayer=v.findViewById(R.id.vvExoPlayer);
        simpleExoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
        videoExoPlayer.setPlayer(simpleExoPlayer);

        Uri uri = RawResourceDataSource.buildRawResourceUri(R.raw.wegz);
        MediaSource mediaSource = buildMediaSource(uri);
        simpleExoPlayer.prepare(mediaSource, false, false);
        simpleExoPlayer.play();

        return v;
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    @Override
    public void onStop() {
        super.onStop();
        simpleExoPlayer.stop();

    }
}
