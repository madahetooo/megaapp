package com.yat.megaapp.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.yat.megaapp.R;

public class JavaTpointWebsite extends Fragment {

    WebView webViewJavaTpointWebsite;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_java_tpoint_website, container, false);
        webViewJavaTpointWebsite=v.findViewById(R.id.webViewJavaTpointWebstie);

        webViewJavaTpointWebsite.getSettings().setJavaScriptEnabled(true);  // enable images

        webViewJavaTpointWebsite.setWebViewClient(new WebViewClient()); // view as a client inside the app

        webViewJavaTpointWebsite.loadUrl("https://www.javatpoint.com/"); //load url
        return v;
    }

}