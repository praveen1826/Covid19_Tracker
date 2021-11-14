package com.vitproject.covid19;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class DonateTn extends AppCompatActivity {
    ProgressBar Progress1;
    ImageView miniicon1;
    WebView Web1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate1);

        Progress1=findViewById(R.id.PB1);
        miniicon1=findViewById(R.id.mini_icon1);
        Web1=findViewById(R.id.WV1);

        Progress1.setMax(100);

        Web1.loadUrl("https://ereceipt.tn.gov.in/cmprf/Cmprf");
        Web1.setWebViewClient(new WebViewClient());
        Web1.getSettings().setJavaScriptEnabled(true);
        Web1.setWebChromeClient(new WebChromeClient(){



            @Override
            public void onProgressChanged(WebView view1, int newProgress1) {
                super.onProgressChanged(view1, newProgress1);
                Progress1.setProgress(newProgress1);
            }

            @Override
            public void onReceivedTitle(WebView view1, String title1) {
                super.onReceivedTitle(view1, title1);
            }

            @Override
            public void onReceivedIcon(WebView view1, Bitmap icon1) {
                super.onReceivedIcon(view1, icon1);
                miniicon1.setImageBitmap(icon1);
            }
        });


    }

}
