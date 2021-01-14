package com.yat.megaapp.tools;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.yat.megaapp.R;

public class MobileTrackPDF extends Fragment {

    PDFView pdfView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_mobile_track_p_d_f, container, false);
        pdfView = v.findViewById(R.id.PDFView);


        pdfView.fromAsset("Mobile_Track.pdf").load();

        return v;
    }

}