package com.glowingsoft.bushdigital;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.glowingsoft.bushdigital.Fragments.About;
import com.glowingsoft.bushdigital.Fragments.Categories;
import com.glowingsoft.bushdigital.Fragments.Contact;
import com.glowingsoft.bushdigital.Fragments.Home;
import com.glowingsoft.bushdigital.Fragments.More;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.objectbox.Box;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BottomNavigationActivity extends AppCompatActivity {
    private static final String TAG_HOME = "home";
    Box<CategoriesModel> siteModelBox;
    Box<CategoryModelLocal> categorybos;
    Box<CategoryModelMainList> categoryModelMainListBox;
    public static String CURRENT_TAG = TAG_HOME;
    private Handler mHandler;

    SharedPreferences mSharedPreferences;
    public static final String PREFERENCE = "preference";
    public static final String PREF_SKIP_LOGIN = "skip_login";
    public static final String PREF_TYPE = "type";
    public static final String PREF_COMPANYID = "com pany";
    public static final String PREF_LOCATIONID = "location";
    public static final String PREF_TERMINALID = "terminal";
    public static final String PREF_USER_EMAIL = "email";
    public static final String PREF_ID = "1";
    public static Activity login;
    List<String> cat = new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    loadHomeFragment(1);
                    return true;

                case R.id.navigation_categories:
                    loadHomeFragment(2);
                    return true;

                case R.id.navigation_about:
                    loadHomeFragment(3);
                    return true;

                case R.id.navigation_contact:
                    loadHomeFragment(4);
                    return true;

                case R.id.navigation_more:
                    loadHomeFragment(5);
                    return true;

            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mHandler = new Handler();
        siteModelBox = ((MyApp) Objects.requireNonNull(this).getApplication())
                .getBoxStore()
                .boxFor(CategoriesModel.class);
        categorybos = ((MyApp) Objects.requireNonNull(this).getApplication())
                .getBoxStore()
                .boxFor(CategoryModelLocal.class);
        categoryModelMainListBox = ((MyApp) Objects.requireNonNull(this).getApplication())
                .getBoxStore()
                .boxFor(CategoryModelMainList.class);
        gethome();
        Dexter.withActivity(this)
                .withPermissions(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
            }
        }).check();

        loadHomeFragment(0);
    }

    private void loadHomeFragment(final int i) {
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment(i);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.disallowAddToBackStack();
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }


    @Override
    public void onBackPressed() {
    }

    @SuppressLint("SetTextI18n")
    private Fragment getHomeFragment(int i) {
        switch (i) {
            case 1:
//                mTextMessage.setText("Home");
//                mTextMessage.setVisibility(View.VISIBLE);
                return new Home();
            case 2:
//                mTextMessage.setText("Categories");
//                mTextMessage.setVisibility(View.VISIBLE);
                return new Categories();
            case 3:
//                mTextMessage.setText("About");
//                mTextMessage.setVisibility(View.VISIBLE);
                return new About();
            case 4:
//                mTextMessage.setText("Contact");
//                mTextMessage.setVisibility(View.VISIBLE);
                return new Contact();
            case 5:
//                mTextMessage.setText("More");
//                mTextMessage.setVisibility(View.VISIBLE);
                return new More();
            default:
                return new Home();
        }
    }

    public void gethome() {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SplashScreenActivity.ApiInterface service1 = retrofit1.create(SplashScreenActivity.ApiInterface.class);
        Call<SplashScreenActivity.BushModel> user1 = service1.home();
        user1.enqueue(new Callback<SplashScreenActivity.BushModel>() {


            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<SplashScreenActivity.BushModel> call,
                                   Response<SplashScreenActivity.BushModel> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == true) {
                        cat.clear();
                        siteModelBox.removeAll();
                        categoryModelMainListBox.removeAll();
                        categorybos.removeAll();
                        cat = response.body().getCategoriesTitles();
                        List<List<SplashScreenActivity.Category>> lists = new ArrayList<>();
                        lists.clear();
                        lists = response.body().getCategories();
                        for (int i = 0; i < cat.size(); i++) {
                            CategoriesModel siteModel = new CategoriesModel();
                            siteModel.setTitle("" + cat.get(i) + "");
                           ;
                            siteModelBox.put(siteModel);
                        }
                        for (int i = 0; i < lists.size(); i++) {
                            CategoryModelMainList categoryModelMainList = new CategoryModelMainList();
                            for (int j = 0; j < lists.get(i).size(); j++) {
                                CategoryModelLocal categoryModelLocal = new CategoryModelLocal();
                                categoryModelLocal.setCategory(lists.get(i).get(j).getCategory());
                                categoryModelLocal.setTitle(lists.get(i).get(j).getTitle());
                                categoryModelLocal.setDescription(lists.get(i).get(j).getDescription());
                                categoryModelLocal.setImage(lists.get(i).get(j).getImage());
                                categoryModelLocal.setPOne(lists.get(i).get(j).getPOne());
                                categoryModelLocal.setPThree(lists.get(i).get(j).getPThree());
                                categoryModelLocal.setPTwo(lists.get(i).get(j).getPTwo());
                                categorybos.put(categoryModelLocal);
                            }
                            List<CategoryModelLocal> categoryModelLocals = categorybos.getAll();
                            categoryModelMainList.setCategoryModelLocals(categoryModelLocals);
                            categoryModelMainListBox.put(categoryModelMainList);
                        }

                    } else {
                        Toast.makeText(BottomNavigationActivity.this, "Invalid ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    BufferedReader reader = null;
                    StringBuilder sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String finallyError = sb.toString();
                    Log.e("TGED", "ERORRRRRRR" + finallyError);
                    Toast.makeText(BottomNavigationActivity.this, "Not Success", Toast.LENGTH_SHORT).show();
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onFailure(Call<SplashScreenActivity.BushModel> call, Throwable t) {
//            progressBar.setVisibility(View.GONE);
                Toast.makeText(BottomNavigationActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();

            }


        });
    }

}
