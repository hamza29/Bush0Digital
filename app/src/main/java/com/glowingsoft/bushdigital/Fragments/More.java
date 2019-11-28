package com.glowingsoft.bushdigital.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.glowingsoft.bushdigital.PaymentActivity;
import com.glowingsoft.bushdigital.PrivacyPolicyActivity;
import com.glowingsoft.bushdigital.R;
import com.glowingsoft.bushdigital.TermsActivity;

import static com.glowingsoft.bushdigital.BottomNavigationActivity.CURRENT_TAG;

public class More extends Fragment {
    CardView payment, privacy, terms, call, email;
    View view1;
    ImageView Fb, Twitter;
    public static String FACEBOOK_URL = "https://www.facebook.com/BushDigitalProductionRental";
    public static String FACEBOOK_PAGE_ID = "BushDigitalProductionRental";

    public More() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        payment = view.findViewById(R.id.payment);
        Fb = view.findViewById(R.id.facebook);
        Twitter = view.findViewById(R.id.twitter);
        privacy = view.findViewById(R.id.privacy);
        terms = view.findViewById(R.id.terms);
        call = view.findViewById(R.id.call);
        email = view.findViewById(R.id.email);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TermsActivity.class);
                startActivity(intent);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uri = "tel:+905338219873";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);

            }
        });
        view1 = getActivity().findViewById(R.id.frame);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contact();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(view1.getId(), fragment, CURRENT_TAG);
                fragmentTransaction.disallowAddToBackStack();
                fragmentTransaction.commitAllowingStateLoss();
            }
        });
        Fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAppInstalled()) {
                    Toast.makeText(getActivity(), "facebook app already installed", Toast.LENGTH_SHORT).show();
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(getActivity());
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);

                } else {
                    Toast.makeText(getActivity(), "facebook app not installed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.orca", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }


    public boolean isAppInstalled() {
        try {
            getActivity().getApplicationContext().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
