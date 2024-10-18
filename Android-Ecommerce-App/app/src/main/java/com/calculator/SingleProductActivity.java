package com.calculator;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class SingleProductActivity extends AppCompatActivity {

    TextView singleHeadLine, singlePrice, singleBrand, singleProductType, singleAboutProduct, singleOrigin;
    ImageView singleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        singleHeadLine = findViewById(R.id.singleHeadLine);
        singleBrand = findViewById(R.id.singleBrand);
        singleProductType = findViewById(R.id.singleProductType);
        singlePrice = findViewById(R.id.singlePrice);
        singleAboutProduct = findViewById(R.id.singleAboutProduct);
        singleOrigin = findViewById(R.id.singleOrigin);

        singleImage = findViewById(R.id.singleImage);

        Picasso.get().load(getIntent().getStringExtra("singleImage"))
                .placeholder(R.drawable.upload)
                .into(singleImage);

        singleHeadLine.setText(getIntent().getStringExtra("singleHeadLine"));
        singlePrice.setText(getIntent().getStringExtra("singlePrice"));
        singleBrand.setText(getIntent().getStringExtra("singleBrand"));
        singleProductType.setText(getIntent().getStringExtra("singleProductType"));
        singleAboutProduct.setText(getIntent().getStringExtra("singleAboutProduct"));
        singleOrigin.setText(getIntent().getStringExtra("singleOrigin"));






    }
}