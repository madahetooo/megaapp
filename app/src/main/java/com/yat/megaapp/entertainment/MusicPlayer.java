
package com.yat.megaapp.entertainment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.yat.megaapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayer extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    SeekBar seekBarWegz;
    ImageView ivPause, ivPlay, ivStop;
    MediaPlayer songWegz;
    int seekBarPosition;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_music_player, container, false);
        ivPause = v.findViewById(R.id.ivPause);
        seekBarWegz = v.findViewById(R.id.seekBar);
        ivPlay = v.findViewById(R.id.ivPlay);
        ivStop = v.findViewById(R.id.ivStop);
        ivPause.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivStop.setOnClickListener(this);
        seekBarWegz.setOnSeekBarChangeListener(this);
//        songWegz = MediaPlayer.create(getBaseContext(), R.raw.wegzson);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (songWegz != null) {
                    seekBarWegz.setProgress(songWegz.getCurrentPosition());

                }
            }
        }, 0, 500);
        return v;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivPlay:
                if (songWegz == null) {
                    songWegz = MediaPlayer.create(getActivity(), R.raw.wegzson);
                    seekBarWegz.setMax(songWegz.getDuration());
                    songWegz.start();

                }
                else if (!songWegz.isPlaying()){
                    songWegz.seekTo(seekBarPosition);
                    songWegz.start();
                }
                break;
            case R.id.ivPause:
                songWegz.pause();
                seekBarPosition = songWegz.getCurrentPosition();
                break;
            case R.id.ivStop:
                if (songWegz != null) {
                    seekBarWegz.setProgress(0);
                    songWegz.stop();
                    songWegz = null;
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    songWegz.seekTo(progress);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}