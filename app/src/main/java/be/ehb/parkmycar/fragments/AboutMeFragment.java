package be.ehb.parkmycar.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import be.ehb.parkmycar.R;


public class AboutMeFragment extends Fragment {


    public AboutMeFragment() {

    }

    private View.OnClickListener websiteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW);
            Uri url = Uri.parse("https://www.linkedin.com/in/patrick-pelszynski-17843819a/");
            webIntent.setData(url);
            startActivity(webIntent);
        }
    };
    private View.OnClickListener sendMail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mailIntent = new Intent(Intent.ACTION_VIEW);
            Uri mail = Uri.parse("mailto:patrick.pelszynski@student.ehb.be");
            mailIntent.setData(mail);
            startActivity(mailIntent);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_me,container,false);
        Button  btnWeb =rootView.findViewById(R.id.btn_web);
        btnWeb.setOnClickListener(websiteListener);
        Button  btnMail =rootView.findViewById(R.id.btn_mail);
        btnMail.setOnClickListener(sendMail);

        return rootView;
    }
}