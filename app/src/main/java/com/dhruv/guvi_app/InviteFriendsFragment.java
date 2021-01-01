package com.dhruv.guvi_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class InviteFriendsFragment extends Fragment implements View.OnClickListener {

    ImageView fb,whatsapp,twitter,telegram;
    String sendStringApp = "Hi, I invite you to check out the awesome learning app GUVI\n" + "\n" + "https://play.google.com/store/apps/details?id=com.guviapp";
    String sendStringWeb = "Hi, I invite you to check out the awesome learning app GUVI\n";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invite_friends, container, false);
        fb = root.findViewById(R.id.img_facebook);
        whatsapp = root.findViewById(R.id.img_whatsapp);
        twitter = root.findViewById(R.id.img_twitter);
        telegram = root.findViewById(R.id.img_telegram);
        fb.setOnClickListener(this);
        whatsapp.setOnClickListener(this);
        twitter.setOnClickListener(this);
        telegram.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_facebook:

                try {
                    Intent share=new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_TEXT, sendStringApp);
                    share.setPackage("com.facebook.katana");
                    startActivity(share);
                }catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/sharer/sharer.php?t=" + sendStringWeb + "&u=" + "https://play.google.com/store/apps/details?id=com.guviapp")));
                }

                break;
            case R.id.img_whatsapp:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?&text=" + sendStringApp)));
                break;
            case R.id.img_twitter:
                try {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_TEXT, sendStringApp);
                    share.setPackage("com.twitter.android"); //Facebook App package
                    startActivity(share);
                }catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/share?text=" + sendStringWeb + "&url=" + "https://play.google.com/store/apps/details?id=com.guviapp")));
                }

                break;
            case R.id.img_telegram:
                try {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.setData(Uri.parse("http://telegram.me/myId"));
                    share.putExtra(Intent.EXTRA_TEXT, sendStringApp);
                    share.setPackage("org.telegram.messenger");
                    startActivity(share);
                }catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/share/url?text=" + sendStringWeb + "&url=" + "https://play.google.com/store/apps/details?id=com.guviapp")));
                }
                break;
        }
    }
}