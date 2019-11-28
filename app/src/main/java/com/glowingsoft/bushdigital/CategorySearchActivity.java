package com.glowingsoft.bushdigital;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategorySearchActivity extends AppCompatActivity {
    EditText Search;
    RecyclerView recyclerView;
    SlimAdapter slimAdapter;
    final int DRAWABLE_LEFT = 0;
    final int DRAWABLE_TOP = 1;
    final int DRAWABLE_RIGHT = 2;
    final int DRAWABLE_BOTTOM = 3;
    ImageView sear;
ProgressBar progressBar;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);
        Search = findViewById(R.id.searchEdit);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        sear = findViewById(R.id.search);
        sear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Search.getText().toString().trim().isEmpty()) {
                    getsearchresult();
                } else {
                    Toast.makeText(CategorySearchActivity.this, "Search Cannot be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView = findViewById(R.id.recyler);
        Search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(CategorySearchActivity.this, "Searched", Toast.LENGTH_SHORT).show();
                    if (!Search.getText().toString().trim().isEmpty()) {
                        getsearchresult();
                    } else {
                        Toast.makeText(CategorySearchActivity.this, "Search Cannot be Empty", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CategorySearchActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Search.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int leftEdgeOfRightDrawable = Search.getRight()
                            - Search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width();
                    if (event.getRawX() >= leftEdgeOfRightDrawable) {
                        // clicked on clear icon
                        Search.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void getsearchresult() {
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SplashScreenActivity.ApiInterface service1 = retrofit1.create(SplashScreenActivity.ApiInterface.class);
        Call<SearchResultModel> user1 = service1.getresult("" + Search.getText().toString());
        user1.enqueue(new Callback<SearchResultModel>() {


            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override

            public void onResponse(Call<SearchResultModel> call,
                                   Response<SearchResultModel> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);

                    if (response.body().getStatus() == true) {
                        slimAdapter = SlimAdapter.create()
                                .register(R.layout.row_item_, new SlimInjector<Datum>() {
                                    @Override
                                    public void onInject(final Datum data, IViewInjector injector) {
                                        injector.text(R.id.product_des, "" + data.getDescription());
                                        injector.text(R.id.product_name, "" + data.getTitle());
                                        injector.with(R.id.product_image, new IViewInjector.Action() {
                                            @Override
                                            public void action(View view) {
                                                Picasso.get().load(data.getImage()).into((ImageView) view);
                                            }
                                        });
                                        Log.e("TGED", "one" + data.getPOne());
                                    }
                                }).attachTo(recyclerView).updateData(response.body().getData());
                    } else {
                        Toast.makeText(CategorySearchActivity.this, "Invalid ", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(CategorySearchActivity.this, "Not Success", Toast.LENGTH_SHORT).show();
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onFailure(Call<SearchResultModel> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
                Toast.makeText(CategorySearchActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();

            }


        });
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
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

    public class SearchResultModel {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

    }
}
