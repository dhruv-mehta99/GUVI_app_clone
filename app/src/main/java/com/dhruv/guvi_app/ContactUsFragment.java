package com.dhruv.guvi_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dhruv.guvi_app.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactUsFragment extends Fragment implements View.OnClickListener , OnMapReadyCallback{


    WebView webView;
    GoogleMap map;
    MapView mapView;
    ImageView fb,insta,twitter,telegram,email;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);
        fb = root.findViewById(R.id.img_facebook_contactus);
        insta = root.findViewById(R.id.img_instagram_contactus);
        twitter = root.findViewById(R.id.img_twitter_contactus);
        telegram = root.findViewById(R.id.img_telegram_contactus);
        email = root.findViewById(R.id.email_arrow);
        webView = root.findViewById(R.id.maps_webview);
        WebSettings webSetting = webView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/maps.html");

        fb.setOnClickListener(this);
        insta.setOnClickListener(this);
        twitter.setOnClickListener(this);
        telegram.setOnClickListener(this);
        email.setOnClickListener(this);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mapView = view.findViewById(R.id.mapView);
//        if(mapView!= null){
//            mapView.onCreate(null);
//            mapView.onResume();
//            mapView.getMapAsync(this);
//
//        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng guvi = new LatLng(12.990345704512437, 80.24278260210788);
        map.addMarker(new MarkerOptions().position(guvi).title("marker on guvi"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(guvi , 20F));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_facebook_contactus :
                gotoUrl("https://www.facebook.com/");
                break;
            case R.id.img_instagram_contactus :
                gotoUrl("https://instagram.com/");
                break;
            case R.id.img_twitter_contactus :
                gotoUrl("https://twitter.com/");
                break;
            case R.id.img_telegram_contactus:
                gotoUrl("https://telegram.org/");
                break;
            case R.id.email_arrow:
                Intent send = new Intent(Intent.ACTION_SENDTO);
                send.setType("text/plain");
                send.setData(Uri.parse("mailto:cs@guvi.in"));
                send.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                startActivity(send);
                break;
        }
    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}