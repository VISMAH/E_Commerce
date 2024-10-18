package com.calculator;

import static com.calculator.Manifest.*;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class ProductUploadActivity extends AppCompatActivity {

    TextView headline, description, price, brand, type, about, origin;
    ImageView uploadBtn, productImage;

    Button submit;
    Uri ImageUri;
    RelativeLayout relativeLayout;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_upload);

        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Please Wait....");
        dialog.setCancelable(false);
        dialog.setTitle("Product Uploading");
        dialog.setCanceledOnTouchOutside(false);


        description = findViewById(R.id.description);
        headline = findViewById(R.id.headline);
        price = findViewById(R.id.price);
        brand = findViewById(R.id.brand);
        type = findViewById(R.id.type);
        about = findViewById(R.id.about);
        origin = findViewById(R.id.origin);
        relativeLayout = findViewById(R.id.relative);

        uploadBtn = findViewById(R.id.uploadbtn);
        productImage = findViewById(R.id.productImage);

        submit = findViewById(R.id.submit);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();
                relativeLayout.setVisibility(View.VISIBLE);
                uploadBtn.setVisibility(View.VISIBLE);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                final StorageReference reference = firebaseStorage.getReference().child("product")
                        .child(System.currentTimeMillis()+"");

                reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                Model model = new Model();
                                model.setProductImage(uri.toString());

                                model.setHeadline(headline.getText().toString());
                                model.setDescription(description.getText().toString());
                                model.setPrice(price.getText().toString());
                                model.setBrand(brand.getText().toString());
                                model.setType(type.getText().toString());
                                model.setAbout(about.getText().toString());
                                model.setOrigin(origin.getText().toString());

                                database.getReference().child("product").push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                Toast.makeText(ProductUploadActivity.this, "Product Upload Successfully", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                dialog.dismiss();
                                                Toast.makeText(ProductUploadActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }
                });

            }
        });



    }

    private void uploadImage() {

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 101);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(ProductUploadActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK){
            ImageUri = data.getData();
            productImage.setImageURI(ImageUri);
        }
    }
}