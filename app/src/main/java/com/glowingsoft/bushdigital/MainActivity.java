package com.glowingsoft.bushdigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView ProductImage;
    TextView NAme, Des, One, Two, Three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        ProductImage = findViewById(R.id.product_image);
        NAme = findViewById(R.id.product_name);
        Des = findViewById(R.id.product_des);
        One = findViewById(R.id.one);
        Two = findViewById(R.id.two);
        Three = findViewById(R.id.three);
        NAme.setText("" + intent.getStringExtra("product_name"));
        Des.setText("" + intent.getStringExtra("product_des"));
        One.setText("" + intent.getStringExtra("Ones"));
        Two.setText("" + intent.getStringExtra("Twos"));
        Three.setText("" + intent.getStringExtra("Threes"));
        Log.e("TGED", "one" + intent.getStringExtra("Ones"));

        Picasso.get().load(intent.getStringExtra("product_image")).into((ImageView) ProductImage);

    }
}
