package com.stfalcon.chatkit.sample.features.demo.custom.layout;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.sample.R;

public class ShowImageFullScreenActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "cshowimagefullscreenactivity.image_url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_full_screen);

        ImageView imageView = findViewById(R.id.imageView);
        ViewGroup root = findViewById(R.id.root);

        String imageUrl = getIntent().getStringExtra(IMAGE_URL);
        if (TextUtils.isEmpty(imageUrl)) {
            this.finish();
        }

        Picasso.with(this).load(imageUrl).into(imageView);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullScreen();
            }
        });
    }

    public static void open(Context context, String imageUrl) {
        context.startActivity(new Intent(context, ShowImageFullScreenActivity.class).putExtra(IMAGE_URL, imageUrl));
    }

    public void fullScreen() {
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;

        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (getSupportActionBar() != null && isImmersiveModeEnabled) {
            getSupportActionBar().show();
        } else {
            getSupportActionBar().hide();
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);

    }
}
