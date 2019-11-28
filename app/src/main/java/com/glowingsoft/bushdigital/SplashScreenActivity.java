package com.glowingsoft.bushdigital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.glowingsoft.bushdigital.Fragments.Categories;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.objectbox.Box;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;


public class SplashScreenActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, BottomNavigationActivity.class);
                startActivity(i);
                finish();

            }
        }, 3000);

    }

    public class BushModel {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("categories_titles")
        @Expose
        private List<String> categoriesTitles = new ArrayList<>();
        @SerializedName("categories")
        @Expose
        private List<List<Category>> categories = new ArrayList<>();

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public List<String> getCategoriesTitles() {
            return categoriesTitles;
        }

        public void setCategoriesTitles(List<String> categoriesTitles) {
            this.categoriesTitles = categoriesTitles;
        }

        public List<List<Category>> getCategories() {
            return categories;
        }

        public void setCategories(List<List<Category>> categories) {
            this.categories = categories;
        }

    }

    public class Category {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("p_one")
        @Expose
        private String pOne;
        @SerializedName("p_two")
        @Expose
        private String pTwo;
        @SerializedName("p_three")
        @Expose
        private String pThree;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPOne() {
            return pOne;
        }

        public void setPOne(String pOne) {
            this.pOne = pOne;
        }

        public String getPTwo() {
            return pTwo;
        }

        public void setPTwo(String pTwo) {
            this.pTwo = pTwo;
        }

        public String getPThree() {
            return pThree;
        }

        public void setPThree(String pThree) {
            this.pThree = pThree;
        }

    }

    public interface ApiInterface {
        @GET("api.php")
        Call<BushModel> home();

        @GET("api.php")
        Call<CategorySearchActivity.SearchResultModel> getresult(@Query("search") String s);

    }

}
