package com.navinnayak.android.grupeeassignment;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.navinnayak.android.grupeeassignment.clients.APIClient;
import com.navinnayak.android.grupeeassignment.clients.APIInterface;
import com.navinnayak.android.grupeeassignment.data.DogContract.DogEntry;
import com.navinnayak.android.grupeeassignment.models.DogApiResponse;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private APIInterface apiInterface;

    private String currentImageUrl;
    private ImageView img;
    private ImageView likeImageView;
    private ImageView disLikeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeApiClient();
        initializeViews();
    }

    void initializeApiClient() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    void initializeViews() {
        img = findViewById(R.id.imgView);
        likeImageView = findViewById(R.id.like);
        disLikeImageView = findViewById(R.id.dislike);
        setRandomDogImage();
        setupLikeView();
        setupDislikeView();
    }

    void setRandomDogImage() {
        Call<DogApiResponse> call = this.apiInterface.getRandomImage();
        final MainActivity ctx = this;
        call.enqueue(new Callback<DogApiResponse>() {
            @Override
            public void onResponse(Call<DogApiResponse> call, Response<DogApiResponse> response) {
                if (response.code() == 200) {
                    final String IMAGEURL = response.body().imageUrl;
                    currentImageUrl = IMAGEURL;
                    ctx.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(IMAGEURL).into(img);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DogApiResponse> call, Throwable t) {

            }
        });
    }

    void setupLikeView() {
        this.likeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeToDb();
                setRandomDogImage();
            }
        });
    }

    void setupDislikeView() {
        this.disLikeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRandomDogImage();
            }
        });
    }

    void storeToDb() {
        ContentValues values = new ContentValues();
        SimpleDateFormat dtFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");
        values.put(DogEntry.COLUMN_DOG_BREED, currentImageUrl);
        values.put(DogEntry.COLUMN_TIME_STAMP, dtFormat.format(new Date()));

        Uri newUri = getContentResolver().insert(DogEntry.CONTENT_URI, values);

        if (newUri == null) {
            Toast.makeText(this, getString(R.string.image_not_saved),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.image_saved_successful),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actions_liked_profiles:
                toLikedProfiles();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void toLikedProfiles() {
        Intent intent = new Intent(MainActivity.this, LikedActivity.class);
        startActivity(intent);
    }
}